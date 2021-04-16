package andanyoung.admin4j.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {

  // api接口包扫描路径
  public static final String SWAGGER_SCAN_BASE_PACKAGE = "andanyoung.admin4j";

  public static final String VERSION = "1.0.0";

  @Bean
  public Docket createRestApi() {

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
        .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("admin4j接口文档")
        .description("接口文档")
        .contact(new Contact("andanyoung", "#", "1218853253@qq.com"))
        .version(VERSION)
        .build();
  }
}
