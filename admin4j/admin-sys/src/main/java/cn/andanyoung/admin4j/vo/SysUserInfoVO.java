package cn.andanyoung.admin4j.vo;

import cn.andanyoung.admin4j.dto.SysMenuDTO;
import cn.andanyoung.admin4j.entity.SysRole;
import cn.andanyoung.admin4j.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author andyoung
 */
@Data
public class SysUserInfoVO extends SysUser {

    private static final long serialVersionUID = 215472350043000816L;
    /**
     * 用户角色
     */
    @TableField(exist = false)
    private List<SysRole> roles;

    /**
     * 用户菜单
     */
    @TableField(exist = false)
    private List<SysMenuDTO> menus;
}
