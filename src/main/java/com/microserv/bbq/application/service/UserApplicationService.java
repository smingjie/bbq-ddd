package com.microserv.bbq.application.service;

import com.microserv.bbq.application.repository.UserApplicationRepository;
import com.microserv.bbq.infrastructure.general.commonshare.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.ApplicationService;
import com.microserv.bbq.infrastructure.general.commonshare.page.PageResult;
import com.microserv.bbq.infrastructure.businessshare.user.UserSearchParam;
import com.microserv.bbq.infrastructure.businessshare.user.UserWithRoleItemCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户应用层服务
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Slf4j
@ApplicationService
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserApplicationRepository userAppRepository;

    /**
     * 分页查询，跨多个领域可直接越过领域层通过基础设施层
     *
     * @param userSearchParam 用户查询参数封装
     * @return 分页查询结果
     */
    public PageResult<UserWithRoleItemCO> listUserByPage(UserSearchParam userSearchParam) {
        try {
            return userAppRepository.listUserByPage(userSearchParam);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("方法(listUserByPage)发生未知查询异常", e);
        }
    }
}
