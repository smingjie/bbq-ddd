package com.microserv.bbq.domain.dict;

import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * 领域模型--聚合 ,聚合根取字典类型
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Setter
@Getter
@Accessors(chain = true)
@DomainAggregate
public class DictTypeAgg extends DictTypeVo {
	private List<DictEntity> itemList;

	public DictTypeAgg(String type) {
		super(type);
	}

	private DictTypeAgg fetch() {
		if (Objects.nonNull(this.getType())) {
			this.itemList = RepositoryFactory.get(DictRepository.class).selectByType(this.getType());

			return this;
		}

		return this;
	}

	/**
	 * 根据字典类型来获取此类型的字典聚合
	 *
	 * @param type 字典类型
	 * @return 字典类型聚合结果
	 */
	public static DictTypeAgg of(String type) {
		return new DictTypeAgg(type).fetch();
	}
}


