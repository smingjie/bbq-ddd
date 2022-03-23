package com.microserv.bbq.domain.flow.repository;

import com.microserv.bbq.domain.flow.agg.FlowConfigAgg;
import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;

import java.util.List;


/**
 * @author jockeys
 * @since 2020/4/25
 */
public interface FlowConfigRepository {
	//--查询

	FlowConfigMainEntity selectFlowConfigMainByFlowId(String flowId);
	String  selectFlowIdByFlowCode(String flowCode);
	List<FlowConfigNodeEntity> selectFlowConfigNodesByFlowId(String flowId);
	List<FlowConfigHandlerEntity> selectFlowConfigHandlersByFlowId(String flowId);
	FlowConfigAgg selectFlowConfigAggByFlowId(String flowId);

	//--命令

	boolean insert(FlowConfigMainEntity entity);
	boolean update(FlowConfigMainEntity entity);
	boolean delete(FlowConfigMainEntity entity);
	boolean deleteConfigByFlowId(String flowId);

	void insertBatchNodes(List<FlowConfigNodeEntity> entities);
	boolean insert(FlowConfigNodeEntity entity);
	boolean update(FlowConfigNodeEntity entity);
	boolean delete(FlowConfigNodeEntity entity);
	void deleteNodesByFlowId(String flowId);

	void insertBatchHandlers(List<FlowConfigHandlerEntity> entities);
	boolean insert(FlowConfigHandlerEntity entity);
	boolean update(FlowConfigHandlerEntity entity);
	boolean delete(FlowConfigHandlerEntity entity);
	void deleteHandlersByFlowId(String flowId);

   FlowConfigAgg saveOrUpdate(FlowConfigAgg agg);














}
