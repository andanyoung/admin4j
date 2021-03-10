package andanyoung.admin4j.services;

import andanyoung.admin4j.dto.SysMenuDTO;

import java.util.List;

/**
 * @author andanyoung
 */
public interface SysMenuService {

    /**
     * 根据roleIds 获取后台系统菜单
     * @param roleIds
     * @return 菜单
     */
    List<SysMenuDTO> menus(List<Integer> roleIds);
}
