package com.microserv.bbq.infrastructure.general.common.exception;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;

/**
 * @author jockeys
 * @date 2020/9/13
 */
public class PersistException extends BusinessException{

	public PersistException(ErrorCodeEnum errorCode) {
		super(errorCode);
	}

	public PersistException(String message) {
		super(message);
	}

	public PersistException(String message, Throwable cause) {
		super(message, cause);
	}
}
