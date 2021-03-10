package andanyoung.admin4j.services.impl;

import andanyoung.admin4j.config.security.SysUserDetails;
import andanyoung.admin4j.dao.SysUserDao;
import andanyoung.admin4j.dto.SysMenuDTO;
import andanyoung.admin4j.entity.SysRole;
import andanyoung.admin4j.entity.SysUser;
import andanyoung.admin4j.services.SysMenuService;
import andanyoung.admin4j.services.SysRoleService;
import andanyoung.admin4j.vo.SysUserInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/2 0:37
 */
@Service
public class SysUserService implements UserDetailsService {

    @Resource
    SysUserDao sysUserDao;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
        userQuery.eq("name", userName);
        SysUser sysUser = sysUserDao.selectOne(userQuery);

        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        
        return new SysUserDetails(sysUser);
    }

    public SysUserInfoVO getUserinfo(){

        SysUserDetails userDetails = (SysUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        SysUserInfoVO sysUserInfoVO = new SysUserInfoVO();
        BeanUtils.copyProperties(userDetails.getSysUser(),sysUserInfoVO);

        List<SysRole> roles = sysRoleService.getRolesByUid(sysUserInfoVO.getUid());
        List<SysMenuDTO> menus = sysMenuService.menus(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        sysUserInfoVO.setRoles(roles);
        sysUserInfoVO.setMenus(menus);
        return sysUserInfoVO;
    }

    /**
     * 登出
     */
    public void logout(){

        SysUserDetails userDetails = (SysUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Integer uid = userDetails.getSysUser().getUid();

        SysUser sysUser = new SysUser();
        sysUser.setUid(uid);
        sysUser.setJwtSecret(RandomStringUtils.randomAlphanumeric(6));
        sysUserDao.updateById(sysUser);
    }
}
