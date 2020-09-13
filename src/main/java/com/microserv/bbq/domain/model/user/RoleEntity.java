package com.microserv.bbq.domain.model.user;

import lombok.Data;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
public class RoleEntity {
	private String roleId;
	private String roleCode;
	private String roleName;
	private String roleType;
	private String remark;
	private Integer status;
}
