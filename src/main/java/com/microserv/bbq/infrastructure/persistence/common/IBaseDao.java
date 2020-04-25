package com.microserv.bbq.infrastructure.persistence.common;

import com.microserv.bbq.infrastructure.general.toolkit.ModelConvertUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 数据访问层 抽象类
 *
 * @param <D> domain object
 * @param <P  persist object
 * @author jockeys
 * @since 2020/4/19
 */
public interface IBaseDao<D, P> {

    default P domain2po(D domain, Class<P> targetClass) {
        return ModelConvertUtils.convert(domain, targetClass);
    }

    default List<P> domain2po(List<D> domainList, Class<P> targetClass) {
        return ModelConvertUtils.convert(targetClass, domainList);
    }

    default D po2domain(P po, Class<D> sourceClass) {
        return ModelConvertUtils.convert(po, sourceClass);
    }

    default List<D> po2domain(List<P> poList, Class<D> sourceClass) {
        return ModelConvertUtils.convert(sourceClass, poList);
    }

}
