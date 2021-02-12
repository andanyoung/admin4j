package cn.andanyoung.admin4j.services.impl;

import cn.andanyoung.admin4j.constants.SysConstant;
import cn.andanyoung.admin4j.dao.SysMenuDao;
import cn.andanyoung.admin4j.entity.SysMenu;
import cn.andanyoung.admin4j.services.SysMenuService;
import cn.andanyoung.admin4j.dto.SysMenuDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<SysMenuDTO> menus(List<Integer> roleIds) {

        List<SysMenu> sysMenus = roleIds.contains(SysConstant.SUPER_ADMIN_ROLE_ID) ? sysMenuDao.allMenus() :  sysMenuDao.menusByRole(roleIds);

        List<SysMenuDTO> sysMenuDTOList = new ArrayList<>(sysMenus.size());
        Map<String, Integer> cache = new HashMap<>(16);

        int index = 0;
        for(SysMenu menu : sysMenus){
            if (menu.getParentId() > 0) {

                Integer cacheIndex =  cache.get(menu.getParentId().toString());
                if(cacheIndex == null){
                    continue;
                }
                SysMenuDTO parentSysMenuDTO = sysMenuDTOList.get(cacheIndex);
                parentSysMenuDTO.appendChildren(menu);
            }else {

                SysMenuDTO sysMenuDTO = new SysMenuDTO();
                sysMenuDTO.setChildren(new ArrayList<>());
                BeanUtils.copyProperties(menu, sysMenuDTO);
                sysMenuDTOList.add(sysMenuDTO);
                cache.put(menu.getId().toString(),index);
                index++;
            }
        }
        return sysMenuDTOList;
    }
}
