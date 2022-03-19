package com.microserv.bbq.domain.common.interfaces;

/**
 * CRUD 接口定义或约束
 *
 * @param <DO> 领域对象泛型表达
 * @author mingjie
 * @since 2021/3/19
 */
public interface IDomainCRUD<DO> extends IDomainSaveOrUpdate<DO>, IDomainQuery<DO>, IDomainDelete {
}
