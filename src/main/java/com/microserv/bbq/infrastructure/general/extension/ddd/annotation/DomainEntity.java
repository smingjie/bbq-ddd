package com.microserv.bbq.infrastructure.general.extension.ddd.annotation;

import java.lang.annotation.*;

/**
 * 领域模型标记-实体
 *
 * @author mingjie
 * @date 2022/3/19
 */
@DDDAnnotation
@Retention(RetentionPolicy.SOURCE)  //设置SOURCE相当与注释作用
@Documented
@Target(value = {ElementType.TYPE, ElementType.FIELD})
public @interface DomainEntity {
    /**
     * 是否是聚合根
     */
    boolean isAggregateRoot() default false;
}
