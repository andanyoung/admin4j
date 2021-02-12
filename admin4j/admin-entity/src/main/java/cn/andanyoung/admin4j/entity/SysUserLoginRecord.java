package cn.andanyoung.admin4j.entity;

import cn.andanyoung.admin4j.enums.LoginTypeEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class SysUserLoginRecord implements Serializable {
    private static final long serialVersionUID = -83887678278120427L;

    private Integer id;

    private Integer uid;

    private long ip;

    /**
     * 1: 账号密码；
     */
    private LoginTypeEnum loginType;

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