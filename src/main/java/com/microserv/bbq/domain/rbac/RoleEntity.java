package com.microserv.bbq.domain.rbac;

import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.rbac.repository.UserRoleMenuRepository;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
@Accessors(chain = true)
public class RoleEntity implements IDomainCRUD<RoleEntity> {
	private static UserRoleMenuRepository userRoleMenuRepo = RepositoryFactory.get(UserRoleMenuRepository.class);
	private String roleId;
	private String roleCode;
	private String roleName;
	private String roleType;
	private String remark;
	private Integer status;

	@Override
	public boolean delete() {
		return userRoleMenuRepo.delete(this);
	}

	@Override
	public RoleEntity get() {
		return userRoleMenuRepo.selectRoleById(this.roleId);
	}

	@Override
	public RoleEntity saveOrUpdate() {
		if (this.roleId == null) {
			userRoleMenuRepo.insert(this.setRoleId(SequenceUtils.uuid32()));
		} else {
			userRoleMenuRepo.update(this);
		}
		return this;
	}
}
