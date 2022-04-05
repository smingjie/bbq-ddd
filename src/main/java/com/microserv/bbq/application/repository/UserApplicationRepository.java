package com.microserv.bbq.application.repository;

import com.microserv.bbq.infrastructure.share.page.PageResult;
import com.microserv.bbq.infrastructure.share.user.UserSearchParam;
import com.microserv.bbq.infrastructure.share.user.UserWithRoleItemCO;

/**
 * 用户应用层仓储接口
 *
 * @author mingjie
 * @date 2022/4/5
 */
public interface UserApplicationRepository {
    PageResult<UserWithRoleItemCO> listUserByPage(UserSearchParam param);
}
