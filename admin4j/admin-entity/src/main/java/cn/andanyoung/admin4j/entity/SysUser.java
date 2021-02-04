package cn.andanyoung.admin4j.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2021-02-02 00:34:05
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -29914608731717613L;

    private Integer uid;

    private String name;

    private String mobile;

    @JsonIgnore
    private String password;

    private String avatarUrl;
    /**
     * 是否可用；0；可用
     */
    private Integer status;

    private List<SysRole> roles;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}