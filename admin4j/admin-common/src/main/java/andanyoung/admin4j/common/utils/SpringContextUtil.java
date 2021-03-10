package andanyoung.admin4j.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 在springboot引导类里设置applicationContext 工具类无需实现ApplicationContextAware接口 需要在
 * springboot引导类里设置applicationContext
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

  public static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    SpringContextUtil.applicationContext = applicationContext;
  }

  public static boolean containsBean(String name) {
    return getApplicationContext().containsBean(name);
  }

  public static Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }

  public static <T> T getBean(Class<T> requiredType) {
    return getApplicationContext().getBean(requiredType);
  }

  public static <T> T getBean(String name, Class<T> clazz) {
    return getApplicationContext().getBean(name, clazz);
  }
}
