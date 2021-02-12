package cn.andanyoung.admin4j.services;

import cn.andanyoung.admin4j.entity.SysRole;

import java.util.List;

/**
 * @author andanyoung
 */
public interface SysRoleService {

    /**
     * 根据UID获取 roles
     * @param uid 用户uid
     * @return roles
     */
    public List<SysRole> getRolesByUid(int uid);
}
