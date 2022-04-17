package com.microserv.bbq.infrastructure.general.commonshare.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;


/**
 * @author mingjie
 * @since 2021/03/20
 */
@Slf4j
@ControllerAdvice
public class ResponseJsonFormatHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> clazz = methodParameter.getNestedParameterType();
        // 判断请求是否要包装
        // 除去ResponseEntity及其父类型、ResponseJson类型
        return !clazz.isAssignableFrom(ResponseEntity.class) && !clazz.isAssignableFrom(ResponseJson.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        log.debug("进入返回体重写过程...");
        //返回code-msg-data
        return Objects.isNull(o) ? ResponseJson.success() : ResponseJson.success(o);
    }
}
