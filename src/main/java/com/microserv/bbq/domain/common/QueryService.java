package com.microserv.bbq.domain.common;

import java.util.List;

/**
 * 接口：查询方法
 *
 * @author jockeys
 * @since 2020/4/6
 */
public interface QueryService<M> {
    M get();

    List<M> getList(M model);
}
