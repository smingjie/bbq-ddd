package com.microserv.bbq.domain.repository;

import com.microserv.bbq.domain.model.dict.DictEntity;

import java.util.List;

/**
 * 字典-仓储接口设计
 *
 * @author jockeys
 * @since 2020/4/11
 */
public interface DictRepo {

	//--查询

	DictEntity select(String id);

	DictEntity selectOne(String type, String key);

	List<DictEntity> selectByType(String type);

	List<DictEntity> selectByValue(String valueLike);

	//--命令
	boolean insert(DictEntity item);

	boolean update(DictEntity item);

	boolean delete(DictEntity item);

}
