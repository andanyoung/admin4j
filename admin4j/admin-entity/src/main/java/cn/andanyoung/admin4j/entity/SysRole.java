package cn.andanyoung.admin4j.entity;

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
public class SysRole implements Serializable {
    private static final long serialVersionUID = -83021794488091655L;

    private Integer id;

    private String name;
    /**
     * 角色名称
     */
    private String nameZh;

    private Date createTime;

    private Date updateTime;
}