package com.microserv.bbq.infrastructure.general.extension.ddd.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 领域模型标记-模型转换器
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DDDAnnotation
@Documented
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface DomainConverter {
}
