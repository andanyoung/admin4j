package cn.andanyoung.admin4j.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/1/26 22:15
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    String world() {
        return "Hello World!";
    }

    @RequestMapping("/date")
    String date(@RequestParam Date date, @RequestParam LocalDateTime dateTime, @RequestParam LocalDate localDate) {
        System.out.println(date);
        System.out.println(dateTime);
        System.out.println(localDate);
        return date.toString();
    }


    @RequestMapping("/list")
    String list(@RequestParam List<String> list) {
        System.out.println(list);
        return list.toString();
    }
}
