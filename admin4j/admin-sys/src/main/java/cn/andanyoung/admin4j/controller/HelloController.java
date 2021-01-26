package cn.andanyoung.admin4j.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 22:15
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    String world() {
        int a = 1 / 0;
        return "Hello World!";
    }
}
