package com.microserv.bbq.domain.flow.repository;

import com.microserv.bbq.domain.flow.model.dpo.FlowConfigDPO;
import com.microserv.bbq.domain.flow.model.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigNodeEntity;

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
	FlowConfigDPO selectFlowConfigAggByFlowId(String flowId);
	FlowConfigDPO selectFlowConfigAggByFlowCode(String flowCode);

	//--命令

	boolean insert(FlowConfigMainEntity entity);
	boolean update(FlowConfigMainEntity entity);
	boolean delete(FlowConfigMainEntity entity);
	boolean deleteConfigMainByFlowId(String flowId);

	void insertBatchNodes(List<FlowConfigNodeEntity> entities);
	boolean insert(FlowConfigNodeEntity entity);
	boolean update(FlowConfigNodeEntity entity);
	boolean delete(FlowConfigNodeEntity entity);
	void deleteConfigNodesByFlowId(String flowId);

	void insertBatchHandlers(List<FlowConfigHandlerEntity> entities);
	boolean insert(FlowConfigHandlerEntity entity);
	boolean update(FlowConfigHandlerEntity entity);
	boolean delete(FlowConfigHandlerEntity entity);
	void deleteConfigHandlersByFlowId(String flowId);

   FlowConfigDPO saveOrUpdate(FlowConfigDPO agg);














}
