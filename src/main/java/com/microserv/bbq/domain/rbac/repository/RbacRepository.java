package com.microserv.bbq.domain.rbac.repository;

import com.microserv.bbq.domain.rbac.model.entity.MenuEntity;
import com.microserv.bbq.domain.rbac.model.entity.RoleEntity;

import java.util.List;

/**
 * 仓储接口 RBAC
 *
 * @author jockeys
 * @date 2020/9/13
 */
public interface RbacRepository {
    // 查询
    List<MenuEntity> selectMenuListByUserId(String userId);
    List<RoleEntity> selectRoleListByUserId(String userId);
    MenuEntity selectMenuById(String menuId);
    RoleEntity selectRoleById(String roleId);


    // 命令
    boolean insertListRole(List<RoleEntity> entities);
    boolean insert(RoleEntity entity);
    boolean update(RoleEntity entity);
    boolean delete(RoleEntity entity);


    boolean insertListMenu(List<MenuEntity> entities);
    boolean insert(MenuEntity entity);
    boolean update(MenuEntity entity);
    boolean delete(MenuEntity entity);


}
