package com.microserv.bbq.domain.dict.action;

import com.microserv.bbq.domain.dict.model.entity.DictEntity;
import com.microserv.bbq.domain.dict.model.entity.DictTypeEntity;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 领域模型-领域服务
 *
 * @author jockeys
 */
@Service
@DomainService
@RequiredArgsConstructor
public class DictDomainService {

	private final DictRepository dictRepository;

	/**
	 * 根据字典值模糊查询匹配项
	 *
	 * @param likeValue 模糊匹配值
	 * @return 所有匹配到的字典实体记录
	 */
	public List<DictEntity> getByValue(String likeValue) {
		return dictRepository.searchByValue(likeValue);
	}


	public List<DictTypeEntity> getTypes(String searchTypeName){
		return dictRepository.searchByTypeName(searchTypeName);
	}
}
