package com.microserv.bbq.infrastructure.general.extension.ddd.annotation;

import java.lang.annotation.*;

/**
 * 领域模型标记-聚合根
 *
 * @author mingjie
 * @date 2022/3/19
 */
@DDDAnnotation
@Retention(RetentionPolicy.SOURCE)  //设置SOURCE相当与注释作用
@Documented
@Target(value = {ElementType.FIELD})  //只能放在属性，标识聚合根
public @interface DomainAggregateRoot {
    String alias() default "";

    String description() default "";
}