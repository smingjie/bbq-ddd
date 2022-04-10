package com.microserv.bbq.infrastructure.general.constant;

/**
 * 错误码定义接口
 *
 * @author mingjie
 * @date 2022/4/10
 */
public interface ErrorCode {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 获取错误提示信息
     *
     * @return 错误提示信息
     */
    String getMessage();
}
