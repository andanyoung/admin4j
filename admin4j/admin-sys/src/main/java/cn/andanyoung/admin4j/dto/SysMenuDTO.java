package cn.andanyoung.admin4j.dto;

import cn.andanyoung.admin4j.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author andanyound
 */
@Data
public class SysMenuDTO extends SysMenu implements Serializable  {

    private static final long serialVersionUID = 6991754494212550320L;

    /**
     * sub-menu
     * 子菜单
     */
    private List<SysMenu> children;

    public void appendChildren(SysMenu menu){

        children.add(menu);
    }
}
