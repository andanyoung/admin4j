package andanyoung.admin4j.common.constant.annotations;

import java.lang.annotation.*;

/**
 * 封装全局统一返回JSON格式，value true 支持；value false
 * 不支持 默认不注解支持全局返回
 *
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/3 22:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface GlobalResponseResult {

    boolean value() default true;
}
