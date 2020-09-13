package com.microserv.bbq.domain.model.user;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.UserRoleMenuRepo;
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
public class UserRoleMenuAgg extends UserEntity {
	private static UserRoleMenuRepo userRoleMenuRepo = RepoFactory.get(UserRoleMenuRepo.class);
	List<MenuTreeItem> menuList;
	List<RoleEntity> roleList;

	/**
	 * 菜单树生成
	 *
	 * @param menuList 所有菜单项
	 */
	private List<MenuTreeItem> createMenuTree(List<MenuEntity> menuList) {

		// 先筛选出父元素
		List<MenuEntity> fatMenus = menuList.stream().filter(MenuEntity::isRootMenu).collect(Collectors.toList());

		// 遍历集合
		List<MenuTreeItem> menuTree = fatMenus.stream()
				.map(o -> new MenuTreeItem(o).fetchSubMenuItems(menuList))
				.collect(Collectors.toList());

		return menuTree.isEmpty() ? null : menuTree;
	}


	public UserRoleMenuAgg getMenuTreeByUserId(String userId) {
		List<MenuEntity> allMenus = userRoleMenuRepo.selectMenusByUserId(userId);

		this.menuList = createMenuTree(allMenus);
		return this;
	}

	public UserRoleMenuAgg getRoleListByUserId(String userId) {
		this.roleList = userRoleMenuRepo.selectRoleListByUserId(userId);
		return this;
	}

	/**
	 * 菜单树的记录
	 */
	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	public static class MenuTreeItem extends MenuEntity {
		private List<MenuTreeItem> children;

		public MenuTreeItem(MenuEntity menuEntity) {
			ModelUtils.convert(menuEntity, this);
		}

		public MenuTreeItem fetchSubMenuItems(List<MenuEntity> menuEntityList) {
			if ((menuEntityList == null) || menuEntityList.isEmpty()) {
				return null;
			}

			List<MenuTreeItem> subMenus = menuEntityList.stream()
					.filter(o -> o.isSubMenuOf(this.getMenuId()))
					.map(e -> new MenuTreeItem(e).fetchSubMenuItems(menuEntityList))
					.collect(Collectors.toList());
			return setChildren(subMenus.isEmpty() ? null : subMenus);
		}
	}
}

