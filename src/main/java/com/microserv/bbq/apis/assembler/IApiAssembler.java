package com.microserv.bbq.apis.assembler;


import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;

import java.util.List;

/**
 * api模型转换接口
 *
 * @param <M> domain model
 * @author jockeys
 * @since 2020/4/11
 */
public interface IApiAssembler<M> {


    default M trans2Domain(Object object, Class<M> targetClazz) {
        return ModelUtils.convert(object, targetClazz);
    }

    default List<M> trans2Domain(List<?> objects, Class<M> targetClazz) {
        return ModelUtils.convert(targetClazz, objects);
    }

}
