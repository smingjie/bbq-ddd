package com.microserv.bbq.domain.rbac.model.dpo;

import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.rbac.model.MenuEntity;
import com.microserv.bbq.domain.rbac.model.RoleEntity;
import com.microserv.bbq.domain.rbac.repository.RbacRepository;
import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserRoleMenuDPO extends UserEntity {
	private static RbacRepository rbacRepository = RepositoryFactory.get(RbacRepository.class);
	List<MenuTreeItemEntity> menuList;
	List<RoleEntity> roleList;

	/**
	 * 菜单树生成
	 *
	 * @param menuList 所有菜单项
	 */
	private List<MenuTreeItemEntity> createMenuTree(List<MenuEntity> menuList) {

		// 先筛选出父元素
		List<MenuEntity> fatMenus = menuList.stream().filter(MenuEntity::isRootMenu).collect(Collectors.toList());

		// 遍历集合
		List<MenuTreeItemEntity> menuTree = fatMenus.stream()
				.map(o -> new MenuTreeItemEntity(o).fetchSubMenuItems(menuList))
				.collect(Collectors.toList());

		return menuTree.isEmpty() ? null : menuTree;
	}


	public UserRoleMenuDPO getMenuTreeByUserId(String userId) {
		List<MenuEntity> allMenus = rbacRepository.selectMenuListByUserId(userId);

		this.menuList = createMenuTree(allMenus);
		return this;
	}

	public UserRoleMenuDPO getRoleListByUserId(String userId) {
		this.roleList = rbacRepository.selectRoleListByUserId(userId);
		return this;
	}



	/**
	 * 菜单树的记录
	 */
	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	public static class MenuTreeItemEntity extends MenuEntity {
		private List<MenuTreeItemEntity> children;

		public MenuTreeItemEntity(MenuEntity menuEntity) {
			ModelUtils.convert(menuEntity, this);
		}

		public MenuTreeItemEntity fetchSubMenuItems(List<MenuEntity> menuEntityList) {
			if ((menuEntityList == null) || menuEntityList.isEmpty()) {
				return null;
			}

			List<MenuTreeItemEntity> subMenus = menuEntityList.stream()
					.filter(o -> o.isSubMenuOf(this.getMenuId()))
					.map(e -> new MenuTreeItemEntity(e).fetchSubMenuItems(menuEntityList))
					.collect(Collectors.toList());
			return setChildren(subMenus.isEmpty() ? null : subMenus);
		}
	}
}

