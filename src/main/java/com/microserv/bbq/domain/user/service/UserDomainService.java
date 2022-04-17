package com.microserv.bbq.domain.user.service;

import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.domain.user.vobj.UserDictVObj;
import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;
import com.microserv.bbq.infrastructure.general.commonshare.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainService;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 用户-领域服务
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainService
@RequiredArgsConstructor
public class UserDomainService {
    private final UserRepository userRepository;

    /**
     * 获取用户字典集合
     *
     * @param searchKey 模糊值（username，name，手机号等）
     * @return 满足条件的集合
     */
    List<UserDictVObj> getUserDictBy(String searchKey) {
        return userRepository.searchDictBy(searchKey);
    }

    public boolean isMatchUsernamePassword(String username, String password) {
        return userRepository.isMatchUsernamePassword(username, password);
    }

    public UserEntity getUserEntityWhenUsernamePasswordMatched(String username, String password) {
        if (isMatchUsernamePassword(username, password)) {
            throw new BusinessException(ErrorCodeEnum.USER_USERNAME_OR_PASSWORD_IS_WRONG);
        }
        return userRepository.selectByUsername(username);
    }
}
