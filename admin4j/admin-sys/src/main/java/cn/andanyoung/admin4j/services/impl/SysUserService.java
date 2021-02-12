package cn.andanyoung.admin4j.services.impl;

import cn.andanyoung.admin4j.config.security.SysUserDetails;
import cn.andanyoung.admin4j.dao.SysUserDao;
import cn.andanyoung.admin4j.dto.SysMenuDTO;
import cn.andanyoung.admin4j.entity.SysRole;
import cn.andanyoung.admin4j.entity.SysUser;
import cn.andanyoung.admin4j.services.SysMenuService;
import cn.andanyoung.admin4j.services.SysRoleService;
import cn.andanyoung.admin4j.vo.SysUserInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
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
}
