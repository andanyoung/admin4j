package andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysMenuRole)实体类
 *
 * @author makejava
 * @since 2021-02-02 00:34:05
 */
@Data
@ApiModel("系统菜单和角色管理表-- 角色和权限关联")
public class SysMenuRole implements Serializable {
  private static final long serialVersionUID = -24137292546365605L;

  private Integer id;

  @ApiModelProperty("菜单ID")
  private Integer mId;

  @ApiModelProperty("角色ID")
  private Integer rId;

  @TableField(fill = FieldFill.INSERT)
  private Date createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Date updateTime;
}
