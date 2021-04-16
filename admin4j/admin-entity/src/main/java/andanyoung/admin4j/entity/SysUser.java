package andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author andanyoung
 * @since 2021-02-02 00:34:05
 */
@Data
@ApiModel("系统用户表")
public class SysUser implements Serializable {
  private static final long serialVersionUID = -29914608731717613L;

  @TableId private Integer uid;

  @ApiModelProperty("用户名")
  private String name;

  @ApiModelProperty("手机号")
  private String mobile;

  @JsonIgnore private String password;

  @ApiModelProperty("头像")
  private String avatarUrl;

  @ApiModelProperty("是否可用；0；可用 ")
  private Integer status;

  @JsonIgnore private String jwtSecret;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
