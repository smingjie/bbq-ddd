package com.microserv.bbq.domain.model.user;

import com.microserv.bbq.domain.common.ICrud;
import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.UserRoleMenuRepo;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
@Accessors(chain = true)
public class RoleEntity implements ICrud<RoleEntity> {
	private static UserRoleMenuRepo userRoleMenuRepo = RepoFactory.get(UserRoleMenuRepo.class);
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
