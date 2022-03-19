package com.microserv.bbq.apis.controller;


import com.microserv.bbq.domain.rbac.RoleEntity;
import com.microserv.bbq.domain.rbac.UserRoleMenuAgg;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@RestController
public class UserRoleMenuController {
	@ApiOperation(value = "获取用户的菜单集合")
	@GetMapping("/user/{userId}/menus")
	public List<UserRoleMenuAgg.MenuTreeItem> getMenuTreeByUserId(@PathVariable String userId) {
		return new UserRoleMenuAgg().getMenuTreeByUserId(userId).getMenuList();
	}

	@ApiOperation(value = "获取用户的角色集合")
	@GetMapping("/user/{userId}/roles")
	public List<RoleEntity> getDictByType(@PathVariable String userId) {
		return new UserRoleMenuAgg().getRoleListByUserId(userId).getRoleList();
	}
}

