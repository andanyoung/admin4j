package andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUserRole)实体类
 *
 * @author makejava
 * @since 2021-02-02 00:34:05
 */
@Data
@ApiModel("用户与角色标定表")
public class SysUserRole implements Serializable {
  private static final long serialVersionUID = -67148650829071837L;

  private Integer id;

  @ApiModelProperty("用户UID")
  private Integer uid;

  @ApiModelProperty("角色ID")
  private Integer roleId;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
