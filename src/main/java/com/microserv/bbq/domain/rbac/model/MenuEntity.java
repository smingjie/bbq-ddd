package com.microserv.bbq.domain.rbac.model;

import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.rbac.repository.RbacRepository;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
@ApiModel
@Accessors(chain = true)
public class MenuEntity implements IDomainCRUD<MenuEntity> {

	private static RbacRepository rbacRepository = RepositoryFactory.get(RbacRepository.class);
	//field
	private String menuId;
	private String parentId;
	private String name;
	private String url;
	private Integer type;
	private String icon;
	private Integer orderNum;

	public boolean isRootMenu() {
		return this.type == 0;
	}

	public boolean isSubMenuOf(String parentId) {
		return (this.parentId.equals(parentId));
	}

	@Override
	public boolean delete() {
		return rbacRepository.delete(this);
	}

	@Override
	public MenuEntity fetch() {
		return rbacRepository.selectMenuById(this.menuId);
	}

	@Override
	public MenuEntity saveOrUpdate() {
		if (this.menuId == null) {
			rbacRepository.insert(this.setMenuId(SequenceUtils.uuid32()));
		} else {
			rbacRepository.update(this);
		}
		return this;
	}

}
