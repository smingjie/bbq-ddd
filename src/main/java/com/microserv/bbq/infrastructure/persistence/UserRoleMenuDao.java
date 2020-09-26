package com.microserv.bbq.infrastructure.persistence;

import com.microserv.bbq.domain.model.user.MenuEntity;
import com.microserv.bbq.domain.model.user.RoleEntity;
import com.microserv.bbq.domain.repository.UserRoleMenuRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.mapper.SysMenuMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.SysRoleMapper;
import com.microserv.bbq.infrastructure.persistence.po.SysMenu;
import com.microserv.bbq.infrastructure.persistence.po.SysRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class UserRoleMenuDao implements UserRoleMenuRepo {
	private final SysMenuMapper menuMapper;
	private final SysRoleMapper roleMapper;

	@Override
	public List<MenuEntity> selectMenuListByUserId(String userId) {
		return ModelUtils.convertList( menuMapper.selectMenusByUserId(userId),MenuEntity.class);

	}


	@Override
	public List<RoleEntity> selectRoleListByUserId(String userId) {
		return ModelUtils.convertList( roleMapper.selectRoleListByUserId(userId),RoleEntity.class);
	}

	@Override
	public boolean insertListRole(List<RoleEntity> entities) {
		entities.forEach(o -> roleMapper.insert(ModelUtils.convert(o, SysRole.class)));
		return true;
	}

	@Override
	public boolean insert(RoleEntity entity) {
		return  roleMapper.insert(ModelUtils.convert(entity, SysRole.class))>0;

	}

	@Override
	public boolean update(RoleEntity entity) {
		return  roleMapper.updateById(ModelUtils.convert(entity, SysRole.class))>0;
	}

	@Override
	public boolean delete(RoleEntity entity) {
		return  roleMapper.deleteById(entity.getRoleId() )>0;
	}

	@Override
	public RoleEntity selectRoleById(String roleId) {
		return ModelUtils.convert( roleMapper.selectById(roleId),RoleEntity.class);
	}

	@Override
	public boolean insertListMenu(List<MenuEntity> entities) {
		entities.forEach(o->menuMapper.insert(ModelUtils.convert(o, SysMenu.class)));
		return true;
	}

	@Override
	public boolean insert(MenuEntity entity) {
		return menuMapper.insert(ModelUtils.convert(entity,SysMenu.class))>0;
	}

	@Override
	public boolean update(MenuEntity entity) {
		return menuMapper.updateById(ModelUtils.convert(entity,SysMenu.class))>0;
	}

	@Override
	public boolean delete(MenuEntity entity) {
		return menuMapper.deleteById(entity.getMenuId())>0;
	}

	@Override
	public MenuEntity selectMenuById(String menuId) {
		return ModelUtils.convert( menuMapper.selectById(menuId),MenuEntity.class);
	}
}
