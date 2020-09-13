package com.microserv.bbq.apis;

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
 * @author jockeys
 * @since 2020/3/6
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
		// ResponseJson继承了HashMap此处不用isAssignableFrom方法
		return !clazz.isAssignableFrom(ResponseEntity.class)
				&& !ResponseJson.class.getName().equals(clazz.getName());
	}

	@Override
	public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
	                              MediaType mediaType,
	                              Class<? extends HttpMessageConverter<?>> aClass,
	                              ServerHttpRequest serverHttpRequest,
	                              ServerHttpResponse serverHttpResponse) {

		log.info("进入返回体重写过程...");
		//成功执行但无数据返回，返回code-msg
		//尚未包装的成功数据，此处封装code-msg-data
		return Objects.isNull(o) ? ResponseJson.ok() : ResponseJson.ok(o);
	}
}
