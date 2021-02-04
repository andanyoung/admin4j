package cn.andanyoung.admin4j.config.security;

import cn.andanyoung.admin4j.dao.SysRoleDao;
import cn.andanyoung.admin4j.dao.SysUserDao;
import cn.andanyoung.admin4j.entity.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/2 0:37
 */
@Configuration
public class UserDetailService implements UserDetailsService {

    @Resource
    SysUserDao sysUserDao;

    @Resource
    SysRoleDao sysRoleDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
        userQuery.eq("name", userName);
        SysUser sysUser = sysUserDao.selectOne(userQuery);

        if (ObjectUtils.isEmpty(sysUser)) {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }

        sysUser.setRoles(sysRoleDao.roles(sysUser.getUid()));
        
        return new SysUserDetails(sysUser);
    }
}
