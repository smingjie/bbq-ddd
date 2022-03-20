package com.microserv.bbq.infrastructure.general.extension.annotation.ddd;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * 领域模型标记-仓储接口
 *
 * @author mingjie
 * @date 2022/3/19
 */
@DDDAnnotation
@Documented
@Repository
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface DomainRepository {
}
