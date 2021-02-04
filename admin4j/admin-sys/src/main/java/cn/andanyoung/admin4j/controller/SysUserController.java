package cn.andanyoung.admin4j.controller;

import cn.andanyoung.admin4j.config.security.SysUserDetails;
import cn.andanyoung.admin4j.entity.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/4 23:05
 */
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @GetMapping("userinfo")
    public SysUser userInfo() {

        SysUserDetails userDetails = (SysUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userDetails.getSysUser();
    }
}
