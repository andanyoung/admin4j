package cn.andanyoung.admin4j.services.impl;

import cn.andanyoung.admin4j.services.SysMenuService;
import cn.andanyoung.admin4j.dto.SysMenuDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest()
class SysMenuServiceImplTest {

    @Autowired
    SysMenuService sysMenuService;

    @Test
    void menus() {

        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<SysMenuDTO> menus = sysMenuService.menus(integers);
        System.out.println(menus);

    }
}