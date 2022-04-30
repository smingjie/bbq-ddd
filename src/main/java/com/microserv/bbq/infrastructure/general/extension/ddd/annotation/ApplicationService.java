package com.microserv.bbq.infrastructure.general.extension.ddd.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * DDD注解-应用层服务
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DDDAnnotation
@Documented
@Service
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface ApplicationService {
}
