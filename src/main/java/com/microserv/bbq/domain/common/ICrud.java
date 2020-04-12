package com.microserv.bbq.domain.common;

/**
 * @author jockeys
 * @since 2020/4/12
 */
public interface ICrud<M> extends ISaveOrUpdate<M>,IQuery<M>,IDelete {
}
