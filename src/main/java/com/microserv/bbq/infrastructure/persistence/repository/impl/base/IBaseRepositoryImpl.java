package com.microserv.bbq.infrastructure.persistence.repository.impl.base;

import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;

import java.util.List;

/**
 * 仓储实现-抽象类
 *
 * @param <DO> domain object
 * @param <PO> persist object
 * @author mingjie
 * @since 2022/03/19
 */
public interface IBaseRepositoryImpl<DO, PO> {

    default PO domain2po(DO domain, Class<PO> targetClass) {
        return ModelUtils.convert(domain, targetClass);
    }

    default List<PO> domain2po(List<DO> domainList, Class<PO> targetClass) {
        return ModelUtils.convertList(domainList, targetClass);
    }

    default DO po2domain(PO po, Class<DO> sourceClass) {
        return ModelUtils.convert(po, sourceClass);
    }

    default List<DO> po2domain(List<PO> poList, Class<DO> sourceClass) {
        return ModelUtils.convertList(poList, sourceClass);
    }

}
