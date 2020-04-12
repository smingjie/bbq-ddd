package com.microserv.bbq.infrastructure.general.exception;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;

/**
 * 自定义顶级异常 BusinessException
 *
 * @author jockeys
 * @since 2020/4/6
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_CODE = 500;
    /**
     * 错误码
     */
    protected int code = DEFAULT_CODE;

    /**
     * 错误枚举-初始化
     */
    public BusinessException(ErrorCodeEnum errorCode) {
        this(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    /**
     * 错误消息-初始化
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * 异常消息、异常链-初始化
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return this.code;
    }
}
