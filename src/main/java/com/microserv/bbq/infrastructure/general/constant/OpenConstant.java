package com.microserv.bbq.infrastructure.general.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 全局常量定义
 *
 * @author jockeys
 * @since 2020/2/2
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenConstant {
    /**
     * 超级管理员
     */
    public static final String SUPPER_ADMIN = "admin";

    /**
     * 删除标志位：已删除
     */
    public static final int DELETED_YES = -1;
    /**
     * 删除标志位：正常
     */
    public static final int DELETED_NOT = 0;

    /**
     * 启用状态标志位：启用1
     */
    public static final int ENABLED_TRUE = 1;
    /**
     * 启用状态标志位：禁用0
     */
    public static final int ENABLED_FALSE = 0;
    /**
     * 图形验证码有效期：默认10分钟
     */
    public static final long CAPTCHA_EXPIRE_TIME = 60 * 10L;
}
