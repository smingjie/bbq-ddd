package com.microserv.bbq.infrastructure.persistence.mapper;

import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 工作流配置的节点处理者 Mapper 接口
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
public interface FlowConfigNodeHandlerMapper extends BaseMapper<FlowConfigNodeHandler> {

    @Select(" select * " +
            " from flow_config_node_handler a " +
            " left join flow_config_node b on a.flow_node_id=b.flow_node_id" +
            " where b.flow_id=#{flowId}")
    List<FlowConfigNodeHandler> selectListByFlowId(@Param("flowId") String flowId);

    @Select(" delete from flow_config_node_handler a " +
            " where a.flow_node_id in ( " +
            "    select b.flow_node_id from flow_config_node b where b.flow_id=#{flowId})")
    int deleteListByFlowId(@Param("flowId") String flowId);
}
