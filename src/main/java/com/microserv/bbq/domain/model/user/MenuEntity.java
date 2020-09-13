package com.microserv.bbq.domain.model.user;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.UserRoleMenuRepo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author jockeys
 * @date 2020/9/13
 */
@Data
@ApiModel
public class MenuEntity {

	private static UserRoleMenuRepo userRoleMenuRepo = RepoFactory.get(UserRoleMenuRepo.class);
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

	public boolean isSubMenuOf(String parentId){
		return (this.parentId.equals(parentId));
	}

}
