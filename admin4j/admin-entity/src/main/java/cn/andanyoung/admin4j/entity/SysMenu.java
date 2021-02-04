package cn.andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysMenu)实体类
 *
 * @author makejava
 * @since 2021-02-02 00:34:03
 */
@Data
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 338855916199151480L;

    private Integer id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String icon;

    private Integer keepAlive;

    private Integer requireAuth;

    private Integer parentId;

    private Integer enabled;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}