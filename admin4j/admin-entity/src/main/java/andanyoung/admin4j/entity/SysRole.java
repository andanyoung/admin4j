package andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysRole)实体类
 *
 * @author makejava
 * @since 2021-02-02 00:34:05
 */
@Data
@ApiModel("角色")
public class SysRole implements Serializable {
  private static final long serialVersionUID = -83021794488091655L;

  private Integer id;

  @ApiModelProperty("角色名称")
  private String name;
  /** 角色名称 */
  @ApiModelProperty("角色名称中文")
  private String nameZh;

  @JsonIgnore
  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @JsonIgnore
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
