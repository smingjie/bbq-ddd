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
@Getter
@Setter
public abstract class AbstractBaseDao<D, P> {

    private Class<D> sourceClass;
    private Class<P> targetClass;

    protected P domain2po(D domain) {
        return ModelConvertUtils.convert(domain, targetClass);
    }

    protected List<P> domain2po(List<D> domainList) {
        return ModelConvertUtils.convert(targetClass, domainList);
    }

    protected D po2domain(P po) {
        return ModelConvertUtils.convert(po, sourceClass);
    }

    protected List<D> po2domain(List<P> poList) {
        return ModelConvertUtils.convert(sourceClass, poList);
    }

}
