package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.DictRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 领域模型-领域服务
 *
 * @author jockeys
 */
@Service
public class DictService {

	public List<DictEntity> getByValue(String likeValue) {
	return RepoFactory.get(DictRepo.class).selectByValue(likeValue);
	}
}
