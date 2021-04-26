package andanyoung.admin4j.mbg;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author 12188
 */
public class CodeGeneratorApplication {

  /** 读取控制台内容 */
  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入").append(tip).append("：");
    System.out.println(help);
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotBlank(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    String projectPath = System.getProperty("user.dir");
    String modelName = scanner("请输入模块名");

    String outputDir = projectPath + "/" + modelName + "/src/main/java";
    System.out.println("outputDir: " + outputDir);

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(outputDir);
    gc.setAuthor("andanyoung");
    gc.setOpen(false);
    gc.setSwagger2(true);
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(
        "jdbc:mysql://localhost:3306/admin4j?useUnicode=true&useSSL=false&characterEncoding=utf8");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("123456");

    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setParent("andanyoung.admin4j");
    mpg.setPackageInfo(pc);

    // 设置自定义输出目录（分布式项目使用）
    //    Map<String, String> pathInfo = new HashMap<>();
    //    pathInfo.put(ConstVal.ENTITY_PATH, pojodir);
    //    pathInfo.put(ConstVal.MAPPER_PATH, mapperdir);
    //    pathInfo.put(ConstVal.XML_PATH, mapperdir);
    //    pathInfo.put(ConstVal.SERVICE_PATH, servicedir);
    //    pathInfo.put(ConstVal.SERVICE_IMPL_PATH, serviceImpldir);
    //    pc.setPathInfo(pathInfo);

    String templatePath = "/templates/mapper.xml.vm";
    // 自定义输出配置
    List<FileOutConfig> focList = new ArrayList<>();
    // 自定义配置会被优先输出
    focList.add(
        new FileOutConfig(templatePath) {
          @Override
          public String outputFile(TableInfo tableInfo) {
            // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
            return projectPath
                + "/"
                + modelName
                + "/src/main/resources/mapper/"
                + pc.getModuleName()
                + "/"
                + tableInfo.getEntityName()
                + "Mapper"
                + StringPool.DOT_XML;
          }
        });

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEnableSqlFilter(true);
    // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
    strategy.setEntityLombokModel(true);
    strategy.setRestControllerStyle(true);
    // 公共父类
    // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
    // 写于父类中的公共字段
    // strategy.setSuperEntityColumns("id");

    // 要设置生成哪些表 如果不设置就是生成所有的表
    strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    mpg.execute();
  }
}
