package com.microserv.bbq.infrastructure.general.extension.annotation.ddd;

import java.lang.annotation.*;

/**
 * 领域模型标记-聚合
 *
 * @author mingjie
 * @date 2022/3/19
 */
@DDDAnnotation
@Retention(RetentionPolicy.SOURCE)  //设置SOURCE相当与注释作用
@Documented
@Target(value = {ElementType.TYPE})
public @interface DomainAggregate {
}
