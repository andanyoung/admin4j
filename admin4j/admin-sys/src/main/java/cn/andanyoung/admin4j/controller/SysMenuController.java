package cn.andanyoung.admin4j.controller;

import cn.andanyoung.admin4j.entity.SysMenu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author andanyoung
 */
@RestController
@RequestMapping("/sysMenu/")
public class SysMenuController {


    @GetMapping
    public List<SysMenu> menu(){
        return  null;
    }
}
