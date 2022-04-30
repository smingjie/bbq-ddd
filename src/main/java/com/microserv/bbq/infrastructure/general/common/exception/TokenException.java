package com.microserv.bbq.infrastructure.general.common.exception;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;

/**
 * @author jockeys
 * @since 2020/4/11
 */
public class TokenException extends BusinessException {
    public TokenException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
