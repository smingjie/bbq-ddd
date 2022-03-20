package com.microserv.bbq.domain.dict.service;

import com.microserv.bbq.domain.dict.entity.DictEntity;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainService;
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
}
