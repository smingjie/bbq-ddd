package com.microserv.bbq.domain.repository;

import com.microserv.bbq.domain.model.user.MenuEntity;
import com.microserv.bbq.domain.model.user.RoleEntity;

import java.util.List;

/**
 * @author jockeys
 * @date 2020/9/13
 */
public interface UserRoleMenuRepo {
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
