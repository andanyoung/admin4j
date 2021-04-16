package andanyoung.admin4j.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/4 23:59
 */
@AllArgsConstructor
@ApiModel("登录方式")
public enum LoginTypeEnum {
  @ApiModelProperty("账号密码登录")
  USERNAME_PSD(1);

  @EnumValue private final int code;
}
