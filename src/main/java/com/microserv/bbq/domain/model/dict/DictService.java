package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.infrastructure.persistence.DictDao;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 领域模型-领域服务
 *
 * @author jockeys
 */
@Service
@DomainService
public class DictService {

	/**
	 * 根据字典值模糊查询匹配项
	 *
	 * @param likeValue 模糊匹配值
	 * @return 所有匹配到的字典实体记录
	 */
	public List<DictEntity> getByValue(String likeValue) {
		return RepoFactory.get(DictDao.class).selectByValue(likeValue);
	}
}
