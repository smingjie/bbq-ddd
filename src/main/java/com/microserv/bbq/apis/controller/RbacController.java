package com.microserv.bbq.apis.controller;


import com.microserv.bbq.domain.rbac.entity.RoleEntity;
import com.microserv.bbq.domain.rbac.agg.UserRoleMenuAgg;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户角色权限（RBAC）控制器
 *
 * @author mingjie
 * @since 2022-04-05
 */
@RestController
public class RbacController {
    @ApiOperation(value = "获取用户的菜单集合")
    @GetMapping("/user/{userId}/menus")
    public List<UserRoleMenuAgg.MenuTreeItemEntity> getMenuTreeByUserId(@PathVariable String userId) {
        return new UserRoleMenuAgg().getMenuTreeByUserId(userId).getMenuList();
    }

    @ApiOperation(value = "获取用户的角色集合")
    @GetMapping("/user/{userId}/roles")
    public List<RoleEntity> getDictByType(@PathVariable String userId) {
        return new UserRoleMenuAgg().getRoleListByUserId(userId).getRoleList();
    }
}

