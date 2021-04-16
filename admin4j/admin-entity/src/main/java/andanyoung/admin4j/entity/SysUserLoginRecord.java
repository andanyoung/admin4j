package andanyoung.admin4j.entity;

import andanyoung.admin4j.enums.LoginTypeEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUserLoginRecord)实体类
 *
 * @author makejava
 * @since 2021-02-04 23:43:21
 */
@Data
@ApiModel("系统用户登录")
public class SysUserLoginRecord implements Serializable {
  private static final long serialVersionUID = -83887678278120427L;

  private Integer id;

  @ApiModelProperty("用户UID")
  private Integer uid;

  @ApiModelProperty("用户登录时IP")
  private long ip;

  /** 1: 账号密码； */
  @ApiModelProperty("用户登录方式")
  private LoginTypeEnum loginType;

  @ApiModelProperty("用户登录客户端")
  private String userAgent;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  public void setUserAgent(String userAgent) {
    if (userAgent == null) {
      userAgent = "";
    }
    this.userAgent = userAgent;
  }
}
