package com.microserv.bbq.domain.flow.repository;

import com.microserv.bbq.domain.flow.agg.FlowConfigAgg;

import java.util.List;


/**
 * @author jockeys
 * @since 2020/4/25
 */
public interface FlowConfigRepository {
	//--查询

	FlowConfigAgg.ConfigEntity selectConfigByFlowId(String flowId);
	String  selectFlowIdByFlowCode(String flowCode);

	List<FlowConfigAgg.NodeEntity> selectNodesByFlowId(String flowId);

	List<FlowConfigAgg.HandlerEntity> selectHandlersByFlowId(String flowId);
	//--命令

	boolean insert(FlowConfigAgg.ConfigEntity entity);
	boolean update(FlowConfigAgg.ConfigEntity entity);
	boolean delete(FlowConfigAgg.ConfigEntity entity);
	boolean deleteConfigByFlowId(String flowId);

	boolean insertBatchNodes(List<FlowConfigAgg.NodeEntity> entities);
	boolean insert(FlowConfigAgg.NodeEntity entity);
	boolean update(FlowConfigAgg.NodeEntity entity);
	boolean delete(FlowConfigAgg.NodeEntity entity);
	boolean deleteNodesByFlowId(String flowId);

	boolean insertBatchHandlers(List<FlowConfigAgg.HandlerEntity> entities);
	boolean insert(FlowConfigAgg.HandlerEntity entity);
	boolean update(FlowConfigAgg.HandlerEntity entity);
	boolean delete(FlowConfigAgg.HandlerEntity entity);
	boolean deleteHandlersByFlowId(String flowId);
















}
