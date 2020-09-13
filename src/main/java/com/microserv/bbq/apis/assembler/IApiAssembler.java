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


	/**
	 * 向下转换为领域模型
	 *
	 * @param object      要转换的对象
	 * @param targetClazz 目标类型
	 * @return 目标领域对象
	 */
	default M trans2Domain(Object object, Class<M> targetClazz) {
		return ModelUtils.convert(object, targetClazz);
	}

	/**
	 * 向下转换为领域模型
	 *
	 * @param objects     要转换的对象集合
	 * @param targetClazz 目标类型
	 * @return 目标领域对象集合
	 */
	default List<M> trans2Domain(List<?> objects, Class<M> targetClazz) {
		return ModelUtils.convert(targetClazz, objects);
	}

}
