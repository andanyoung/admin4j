package andanyoung.admin4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"andanyoung.admin4j.*"})
@MapperScan("andanyoung.admin4j.dao")
public class Admin4jApplication {

  public static void main(String[] args) {
    SpringApplication.run(Admin4jApplication.class, args);
  }
}
