package com.microserv.bbq.domain.common.interfaces;

/**
 * 接口：新增或更新方法
 *
 * @author mingjie
 * @since 2021/3/19
 */
@FunctionalInterface
public interface IDomainSaveOrUpdate<DO> {
    DO saveOrUpdate();
}
