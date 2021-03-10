package andanyoung.admin4j.controller;

import andanyoung.admin4j.services.impl.SysUserService;
import andanyoung.admin4j.vo.SysUserInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/4 23:05
 */
@RestController
@RequestMapping("/sys")
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @GetMapping("userinfo")
    public SysUserInfoVO userInfo() {

        return sysUserService.getUserinfo();
    }

    @GetMapping("logout")
    public void userLogout() {

        sysUserService.logout();
    }
}
