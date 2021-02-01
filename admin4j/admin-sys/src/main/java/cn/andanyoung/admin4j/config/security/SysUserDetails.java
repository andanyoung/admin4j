package cn.andanyoung.admin4j.config.security;

import cn.andanyoung.admin4j.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/2 1:06
 */
@Data
public class SysUserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private static final long serialVersionUID = -4001135482362502529L;

    @JsonIgnore
    private final SysUser sysUser;

    public SysUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.getStatus() == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus() == 0;
    }
}
