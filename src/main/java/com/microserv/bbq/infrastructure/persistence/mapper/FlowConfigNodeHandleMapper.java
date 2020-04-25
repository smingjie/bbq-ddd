package com.microserv.bbq.infrastructure.persistence.mapper;

import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandle;
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
public interface FlowConfigNodeHandleMapper extends BaseMapper<FlowConfigNodeHandle> {

    @Select(" select * " +
            " from flow_config_node_handle a " +
            " left join flow_config_node b on a.flow_node_id=b.flow_node_id" +
            " where b.flow_id=#{flowId}")
    List<FlowConfigNodeHandle> selectListByFlowId(@Param("flowId") String flowId);
}
