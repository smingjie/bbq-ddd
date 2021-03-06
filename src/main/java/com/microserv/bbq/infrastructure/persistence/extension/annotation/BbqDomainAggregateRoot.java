package com.microserv.bbq.infrastructure.persistence.extension.annotation;

import java.lang.annotation.*;

/**
 * 领域模型标记-聚合根
 *
 * @author jockeys
 * @date 2020/9/26
 */
@Retention(RetentionPolicy.SOURCE)  //设置SOURCE相当与注释作用
@Documented
@Target(value = {ElementType.TYPE, ElementType.FIELD})
public @interface BbqDomainAggregateRoot {
	String alias() default "";

	String description() default "";
}
