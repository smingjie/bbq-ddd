package com.microserv.bbq.domain.factory;

import com.microserv.bbq.infrastructure.general.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 仓储工厂用来统一获取仓储实现
 *
 * @author jockeys
 * @date 2020/9/12
 */
public class RepoFactory {

	/**
	 * 根据仓储接口类型获取对应实现且默认取值第一个
	 *
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public static <T> T get(Class<T> tClass) {

		Map<String, T> map = ApplicationUtils.getApplicationContext().getBeansOfType(tClass);
		Collection<T> collection = map.values();
		if (collection.isEmpty()) {
			throw new BusinessException("未找到仓储接口(" + tClass.getName() + ")的实现");
		}
		return collection.stream().findFirst().get();
	}
}
