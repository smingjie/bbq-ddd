package com.microserv.bbq.domain.model.dict;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.common.ICrud;
import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.DictRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 领域模型--实体
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DictEntity implements ICrud<DictEntity> {
	//----- field -----//

	private String id;
	private String name;
	private String type;
	private String code;
	private String value;
	private Integer orderNum;
	private String remark;

	//----- constructor -----//

	public DictEntity(String id) {
		this.id = id;
	}

	//----- public method-----//
	@Override
	public DictEntity get() {
		if (!StrUtil.isAllBlank(this.id, this.type, this.code)) {
			DictEntity item = Objects.nonNull(this.id) ?
					RepoFactory.get(DictRepo.class).select(this.id) :
					RepoFactory.get(DictRepo.class).selectOne(this.type, this.code);
			ModelUtils.convert(item, this);
		}
		return this;
	}

	@Override
	public DictEntity saveOrUpdate() {
		if (Objects.nonNull(this.id)) {
			this.id = SequenceUtils.uuid32();
			RepoFactory.get(DictRepo.class).insert(this);
		} else {
			RepoFactory.get(DictRepo.class).update(this);
		}
		return this;
	}

	@Override
	public boolean delete() {
		return RepoFactory.get(DictRepo.class).delete(this);
	}

}
