package com.microserv.bbq.domain.repository;

import com.microserv.bbq.domain.model.flow.FlowConfigAgg;


/**
 * @author jockeys
 * @since 2020/4/25
 */
public interface FlowConfigRepo {
    //--查询

    FlowConfigAgg selectConfigs(String flowId);

    //--命令
    boolean insert(FlowConfigAgg data);

    boolean update(FlowConfigAgg data);

    boolean delete(FlowConfigAgg data);
}
