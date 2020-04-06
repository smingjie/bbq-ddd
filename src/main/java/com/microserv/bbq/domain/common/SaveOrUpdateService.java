package com.microserv.bbq.domain.common;

/**
 * 接口：新增或更新方法
 *
 * @author jockeys
 * @since 2020/4/6
 */
@FunctionalInterface
public interface SaveOrUpdateService<M> {
    M saveOrUpdate();
}
