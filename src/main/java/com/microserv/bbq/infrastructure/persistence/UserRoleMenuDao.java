package com.microserv.bbq.infrastructure.persistence;

import com.microserv.bbq.domain.model.user.MenuEntity;
import com.microserv.bbq.domain.model.user.RoleEntity;
import com.microserv.bbq.domain.repository.UserRoleMenuRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.mapper.SysMenuMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.SysRoleMapper;
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
	public List<MenuEntity> selectMenusByUserId(String userId) {
		return  ModelUtils.convert(MenuEntity.class, menuMapper.selectMenusByUserId(userId));

	}


	@Override
	public List<RoleEntity> selectRoleListByUserId(String userId) {
		return  ModelUtils.convert(RoleEntity.class, roleMapper.selectRoleListByUserId(userId));
	}
}
