package com.microserv.bbq.infrastructure.persistence.repository.impl;

import com.microserv.bbq.infrastructure.persistence.assembler.RbacAssembler;
import com.microserv.bbq.infrastructure.persistence.po.SysMenu;
import com.microserv.bbq.infrastructure.persistence.po.SysRole;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysMenuMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysRoleMapper;
import com.microserv.bbq.domain.rbac.entity.MenuEntity;
import com.microserv.bbq.domain.rbac.entity.RoleEntity;
import com.microserv.bbq.domain.rbac.repository.RbacRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User-RBAC领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class RbacRepositoryImpl implements RbacRepository {
    private final SysMenuMapper menuMapper;
    private final SysRoleMapper roleMapper;
    private final RbacAssembler rbacAssembler;

    @Override
    public List<MenuEntity> selectMenuListByUserId(String userId) {
        List<SysMenu> dbMenus = menuMapper.selectMenusByUserId(userId);
        return dbMenus.stream().map(rbacAssembler::po2domain).collect(Collectors.toList());

    }


    @Override
    public List<RoleEntity> selectRoleListByUserId(String userId) {
        List<SysRole> dbRoles = roleMapper.selectRoleListByUserId(userId);
        return dbRoles.stream().map(rbacAssembler::po2domain).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertListRole(List<RoleEntity> entities) {
        entities.forEach(this::insert);
        return true;
    }

    @Override
    public boolean insert(RoleEntity entity) {
        SysRole sysRole = rbacAssembler.domain2po(entity);
        return roleMapper.insert(sysRole) > 0;
    }

    @Override
    public boolean update(RoleEntity entity) {
        SysRole sysRole = rbacAssembler.domain2po(entity);
        return roleMapper.updateById(sysRole) > 0;
    }

    @Override
    public boolean delete(RoleEntity entity) {
        return roleMapper.deleteById(entity.getRoleId()) > 0;
    }

    @Override
    public RoleEntity selectRoleById(String roleId) {
        SysRole sysRole = roleMapper.selectById(roleId);
        return rbacAssembler.po2domain(sysRole);
    }

    @Override
    public boolean insertListMenu(List<MenuEntity> entities) {
        entities.forEach(this::insert);
        return true;
    }

    @Override
    public boolean insert(MenuEntity entity) {
        SysMenu sysMenu = rbacAssembler.domain2po(entity);
        return menuMapper.insert(sysMenu) > 0;
    }

    @Override
    public boolean update(MenuEntity entity) {
        SysMenu sysMenu = rbacAssembler.domain2po(entity);
        return menuMapper.updateById(sysMenu) > 0;
    }

    @Override
    public boolean delete(MenuEntity entity) {
        return menuMapper.deleteById(entity.getMenuId()) > 0;
    }

    @Override
    public MenuEntity selectMenuById(String menuId) {
        SysMenu sysMenu = menuMapper.selectById(menuId);
        return rbacAssembler.po2domain(sysMenu);
    }
}
