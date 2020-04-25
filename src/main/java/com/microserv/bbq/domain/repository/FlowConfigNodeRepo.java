package com.microserv.bbq.domain.repository;

import com.microserv.bbq.domain.model.dict.DictEntity;

import java.util.List;

/**
 * @author jockeys
 * @since 2020/4/25
 */
public interface FlowConfigNodeRepo {
    //--查询

    DictEntity select(String id);

    DictEntity selectOne(String type, String key);

    List<DictEntity> selectByType(String type);


    //--命令
    boolean insert(DictEntity item);

    boolean update(DictEntity item);

    boolean delete(DictEntity item);
}
