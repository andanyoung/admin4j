package cn.andanyoung.admin4j.dao;

import cn.andanyoung.admin4j.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author andanyoung
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {

    @Select("<script>SELECT menu.* FROM `sys_menu` menu INNER JOIN `sys_menu_role` role on role.m_id=menu.id WHERE menu.is_delete = 0 and role.r_id in <foreach collection=\"roleIds\" item=\"roleId\" index=\"index\" open=\"(\" separator=\",\" close=\")\">\n" +
            " #{roleId} </foreach> ORDER parent_id aes , sort desc</script>")
    List<SysMenu> menusByRole(List<Integer> roleIds);

    @Select("SELECT menu.* FROM `sys_menu` menu WHERE menu.is_delete = 0 ")
    List<SysMenu> allMenus();
}
