package com.microserv.bbq.infrastructure.general.common.security;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;
import com.microserv.bbq.infrastructure.general.common.exception.BusinessException;

/**
 * 用户鉴权信息-上下文
 *
 * @author mingjie
 * @date 2022/3/19
 */
public final class SecurityContext {


    private static final ThreadLocal<LoginUser> securityUserThreadLocal = new ThreadLocal<>();

    private SecurityContext() {
    }

    private static LoginUser getLoginUser() {
        return securityUserThreadLocal.get();
    }

    public static String tryGetLoginUserId() {
        LoginUser securityUser = getLoginUser();
        if (securityUser == null) {
            return null;
        }
        return securityUser.getUserId();
    }

    public static String getLoginUserId() {
        LoginUser securityUser = getLoginUser();
        if (securityUser == null) {
            throw new BusinessException(ErrorCodeEnum.USER_NOT_LOGIN_ERROR);
        }
        return securityUser.getUserId();
    }

    public static void set(LoginUser securityUser) {
        securityUserThreadLocal.set(securityUser);
    }

    public static void clear() {
        if (securityUserThreadLocal.get() != null) {
            securityUserThreadLocal.remove();
        }
    }

}
