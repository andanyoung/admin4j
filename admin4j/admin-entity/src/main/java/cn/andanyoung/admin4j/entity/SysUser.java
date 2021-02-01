package cn.andanyoung.admin4j.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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

    private String password;

    private String avatarUrl;
    /**
     * 是否可用；0；可用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}