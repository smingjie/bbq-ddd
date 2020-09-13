package com.microserv.bbq.infrastructure.persistence.mapper;

import com.microserv.bbq.infrastructure.persistence.po.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/**
	 * 根据角色ids查询菜单集合
	 *
	 * @param roleIds 角色ids
	 * @return 菜单集合
	 */
	@Select("<script>select sm.* from sys_menu sm " +
			" inner join sys_role_menu srm on sm.menu_id=srm.menu_id" +
			" where srm.role_id in " +
			"<foreach collection='roleIds' open='(' item='roleId' separator=',' close=')'>#{roleId}</foreach>" +
			"</script>")
	List<SysMenu> selectMenusByRoleIds(@Param("roleIds") HashSet<String> roleIds);

	/**
	 * 根据用户id询菜单集合
	 *
	 * @param userId 用户id
	 * @return 菜单集合
	 */
	@Select("select sm.* from sys_menu sm " +
			" inner join sys_role_menu srm on sm.menu_id=srm.menu_id" +
			" where srm.role_id in( " +
			" select sur.role_id from sys_user_role sur where sur.user_id=#{userId})")
	List<SysMenu> selectMenusByUserId(@Param("userId") String userId);
}
