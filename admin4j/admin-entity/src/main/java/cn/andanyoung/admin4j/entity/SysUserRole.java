package cn.andanyoung.admin4j.entity;

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
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -67148650829071837L;

    private Integer id;

    private Integer uid;
    /**
     * 角色ID
     */
    private Integer roleId;

    private Date createTime;

    private Date updateTime;
}