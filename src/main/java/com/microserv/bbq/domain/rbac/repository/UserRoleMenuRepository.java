package com.microserv.bbq.domain.rbac.repository;

import com.microserv.bbq.domain.rbac.MenuEntity;
import com.microserv.bbq.domain.rbac.RoleEntity;

import java.util.List;

/**
 * @author jockeys
 * @date 2020/9/13
 */
public interface UserRoleMenuRepository {
	List<MenuEntity> selectMenuListByUserId(String userId);
	List<RoleEntity> selectRoleListByUserId(String userId);

	boolean insertListRole(List<RoleEntity> entities);
	boolean insert(RoleEntity entity);
	boolean update(RoleEntity entity);
	boolean delete(RoleEntity entity);
	RoleEntity selectRoleById(String roleId);

	boolean insertListMenu(List<MenuEntity> entities);
	boolean insert(MenuEntity entity);
	boolean update(MenuEntity entity);
	boolean delete(MenuEntity entity);
	MenuEntity selectMenuById(String menuId);

}
