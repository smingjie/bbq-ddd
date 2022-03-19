package com.microserv.bbq.apis;

import com.microserv.bbq.infrastructure.general.constant.ErrorCodeEnum;
import com.microserv.bbq.infrastructure.general.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 统一异常处理器
 * java中异常分类 Exception->(IOException & RuntimeException)，详情请自行查阅文档
 *
 * @author jockeys
 * @since 2020/2/2
 */

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * RuntimeException异常捕获并统一处理
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseJson handle(RuntimeException e) {
        log.info("统一异常处理过程,运行时异常[{}]：{}", e.getClass(), e.getMessage());
        if (e instanceof BusinessException) {
            BusinessException ex = (BusinessException) e;
            log.error("系统业务服务异常-{}：{}", e.getClass(), e.getMessage());
            return ResponseJson.error(ex.getCode(), ex.getMessage());
        }
        if (e instanceof DataAccessException) {
            log.error("系统仓储服务异常-{}：{}", e.getClass(), e.getMessage());
            return ResponseJson.error(ErrorCodeEnum.DATE_ACCESS_FAIL);
        }
        return ResponseJson.error(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseJson handle(Exception e) {
        if (e instanceof NoHandlerFoundException) {
            NoHandlerFoundException ex = (NoHandlerFoundException) e;
            log.error("统一异常处理过程,请求方法不存在-{}：{}", e.getClass(), e.getMessage());
            return ResponseJson.error(ErrorCodeEnum.NOT_FOUND.getCode(), ex.getMessage());
        }
        log.error("统一异常处理过程,系统内部服务异常-{}：{}", e.getClass(), e.getMessage());
        return ResponseJson.error(ErrorCodeEnum.INTERNAL_SERVER_ERROR);
    }


}
