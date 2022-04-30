package com.microserv.bbq.infrastructure.general.extension.ddd.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * 领域模型标记-领域服务
 *
 * @author mingjie
 * @date 2022/3/19
 */
@DDDAnnotation
@Documented
@Service
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface DomainService {
}
