package cn.andanyoung.admin4j.dao;

import cn.andanyoung.admin4j.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author andanyoung
 * @version 1.0
 * @date 2021/2/5 1:44
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    @Select("SELECT r.id,r.`name`,r.`name_zh`,ur.role_id FROM sys_user_role ur INNER JOIN sys_role r on r.id = ur.role_id WHERE ur.uid = #{uid}")
    List<SysRole> roles(Integer uid);
}
