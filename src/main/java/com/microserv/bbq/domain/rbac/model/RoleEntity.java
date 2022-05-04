package com.microserv.bbq.domain.rbac.model;

import com.microserv.bbq.domain.common.interfaces.IDomainCRUD;
import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.rbac.repository.RbacRepository;
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
	private static RbacRepository rbacRepository = RepositoryFactory.get(RbacRepository.class);
	private String roleId;
	private String roleCode;
	private String roleName;
	private String roleType;
	private String remark;
	private Integer status;

	@Override
	public boolean delete() {
		return rbacRepository.delete(this);
	}

	@Override
	public RoleEntity fetch() {
		return rbacRepository.selectRoleById(this.roleId);
	}

	@Override
	public RoleEntity saveOrUpdate() {
		if (this.roleId == null) {
			rbacRepository.insert(this.setRoleId(SequenceUtils.uuid32()));
		} else {
			rbacRepository.update(this);
		}
		return this;
	}
}
