package andanyoung.admin4j.controller;

import andanyoung.admin4j.services.impl.SysUserService;
import andanyoung.admin4j.vo.SysUserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/4 23:05
 */
@Api("系统用户管理")
@RestController
@RequestMapping("/sys")
public class SysUserController {

  @Resource SysUserService sysUserService;

  @GetMapping("userinfo")
  @ApiOperation("用户数据")
  public SysUserInfoVO userInfo() {

    return sysUserService.getUserinfo();
  }

  @GetMapping("logout")
  @ApiOperation("用户退出")
  public void userLogout() {

    sysUserService.logout();
  }
}
