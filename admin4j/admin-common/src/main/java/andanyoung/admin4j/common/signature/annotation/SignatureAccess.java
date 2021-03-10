package andanyoung.admin4j.common.signature.annotation;

import java.lang.annotation.*;

/**
 * 用于标记接口需不需要签名。 value = true 或者没有该注解说明需要支持签名
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SignatureAccess {

  boolean value() default true;
}
