package andanyoung.admin4j.services.impl;

import andanyoung.admin4j.dao.SysRoleDao;
import andanyoung.admin4j.entity.SysRole;
import andanyoung.admin4j.services.SysRoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author andanyoung
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    SysRoleDao sysRoleDao;

    @Override
    @Cacheable(value = "DEFAULT")
    public List<SysRole> getRolesByUid(int uid){

        return sysRoleDao.roles(uid);
    }
}
