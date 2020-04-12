package com.microserv.bbq.infrastructure.persistence.base;


import com.microserv.bbq.infrastructure.general.toolkit.ConvertUtils;

import java.util.List;

/**
 * 领域层仓储接口的抽象实现
 *
 * @param <D> dbo
 * @param <M> domain
 * @author jockeys
 * @since 2020/4/11
 */
public interface IDaoAssembler<D, M> {


    default M transferDomain(D dbo, Class<M> targetClazz) {
        return ConvertUtils.convert(dbo, targetClazz);
    }

    default List<M> transferDomain(List<D> dbos, Class<M> targetClazz) {
        return ConvertUtils.convert(targetClazz, dbos);
    }

    default D transferDbo(M domain, Class<D> targetClazz) {
        return ConvertUtils.convert(domain, targetClazz);
    }

    default List<D> transferDbo(List<M> domains, Class<D> targetClazz) {
        return ConvertUtils.convert(targetClazz, domains);
    }


}
