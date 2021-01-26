package cn.andanyoung.admin4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"cn.andanyoung.admin4j.*"})
public class Admin4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(Admin4jApplication.class, args);
    }
}
