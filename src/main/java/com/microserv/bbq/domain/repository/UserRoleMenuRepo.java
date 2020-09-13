package com.microserv.bbq.domain.repository;

import com.microserv.bbq.domain.model.user.MenuEntity;
import com.microserv.bbq.domain.model.user.RoleEntity;

import java.util.List;

/**
 * @author jockeys
 * @date 2020/9/13
 */
public interface UserRoleMenuRepo {
	List<MenuEntity> selectMenusByUserId(String userId);
	List<RoleEntity> selectRoleListByUserId(String userId);
}
