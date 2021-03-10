package andanyoung.admin4j.services.impl;

import andanyoung.admin4j.constants.SysConstant;
import andanyoung.admin4j.dao.SysMenuDao;
import andanyoung.admin4j.dto.SysMenuDTO;
import andanyoung.admin4j.entity.SysMenu;
import andanyoung.admin4j.services.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author andanyoung
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuDao sysMenuDao;

    @Override
    @Cacheable(value = "DEFAULT")
    public List<SysMenuDTO> menus(List<Integer> roleIds) {

        List<SysMenu> sysMenus = roleIds.contains(SysConstant.SUPER_ADMIN_ROLE_ID) ? sysMenuDao.allMenus() :  sysMenuDao.menusByRole(roleIds);

        List<SysMenuDTO> sysMenuDTOList = new ArrayList<>(sysMenus.size());
        List<SysMenuDTO> subMenuDTOList = new ArrayList<>(sysMenus.size());
        Map<String, Integer> cache = new HashMap<>(16);

        int index = 0;
        for(SysMenu menu : sysMenus){

            SysMenuDTO currentMenu = new SysMenuDTO();
            currentMenu.setChildren(new ArrayList<>());
            BeanUtils.copyProperties(menu, currentMenu);

            if (menu.getParentId() > 0) {

                Integer cacheIndex =  cache.get(menu.getParentId().toString());
                if(cacheIndex == null){
                    //添加到二级以上目录
                    for(SysMenuDTO subMenu : subMenuDTOList){
                        if (subMenu.getId().equals(currentMenu.getParentId())) {
                            subMenu.appendChildren(currentMenu);
                            break;
                        }
                    }
                }else {
                    //添加到一级目录
                    SysMenuDTO parentSysMenuDTO = sysMenuDTOList.get(cacheIndex);
                    parentSysMenuDTO.appendChildren(currentMenu);
                }

                subMenuDTOList.add(currentMenu);
            }else {


                sysMenuDTOList.add(currentMenu);
                cache.put(menu.getId().toString(),index);
                index++;
            }
        }
        return sysMenuDTOList;
    }
}
