package com.microserv.bbq.domain.common.factory;

import com.microserv.bbq.infrastructure.general.common.exception.PersistException;
import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 仓储工厂用来统一获取仓储实现
 *
 * @author jockeys
 * @date 2022/03/19
 */
public class RepositoryFactory {

	/**
	 * 根据仓储接口类型获取对应实现且默认取值第一个
	 *
	 * @param tClass 目标仓储接口类型
	 * @param <T>    目标类型
	 * @return 如果不是指定实现，默认获得第一个实现Bean
	 */
	public static <T> T get(Class<? extends T> tClass) {

		Map<String, ? extends T> map = ApplicationUtils.getApplicationContext().getBeansOfType(tClass);
		Collection<? extends T> collection = map.values();
		if (collection.isEmpty()) {
			throw new PersistException("未找到仓储接口或其指定的实现:" + tClass.getSimpleName() );
		}
		return collection.stream().findFirst().get();
	}
}
