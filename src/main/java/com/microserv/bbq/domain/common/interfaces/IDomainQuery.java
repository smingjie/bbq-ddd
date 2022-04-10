package com.microserv.bbq.domain.common.interfaces;

/**
 * 接口：查询方法
 *
 * @param <DO> 领域对象泛型表达
 * @author jockeys
 * @since 2022/3/19
 */
public interface IDomainQuery<DO> {
    DO fetch();
}
