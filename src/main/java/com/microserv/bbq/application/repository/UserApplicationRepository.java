package com.microserv.bbq.application.repository;

import com.microserv.bbq.infrastructure.general.common.page.PageResult;
import com.microserv.bbq.infrastructure.general.module.user.UserSearchParam;
import com.microserv.bbq.infrastructure.general.module.user.UserWithRoleItemCO;

/**
 * 用户应用层仓储接口
 *
 * @author mingjie
 * @date 2022/4/5
 */
public interface UserApplicationRepository {
    PageResult<UserWithRoleItemCO> listUserByPage(UserSearchParam param);
}
