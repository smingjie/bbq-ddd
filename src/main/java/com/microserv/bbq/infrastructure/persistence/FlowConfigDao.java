package com.microserv.bbq.infrastructure.persistence;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.model.flow.FlowConfigAgg;
import com.microserv.bbq.domain.repository.FlowConfigRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigNodeHandlerMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigNodeMapper;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class FlowConfigDao implements FlowConfigRepo {

	private final FlowConfigMapper flowConfigMapper;
	private final FlowConfigNodeMapper flowConfigNodeMapper;
	private final FlowConfigNodeHandlerMapper flowConfigNodeHandleMapper;


	List<FlowConfigNode> selectConfigNodes(String flowId) {
		return ChainWrappers.lambdaQueryChain(flowConfigNodeMapper)
				.eq(FlowConfigNode::getFlowId, flowId)
				.orderByAsc(FlowConfigNode::getSequence, FlowConfigNode::getCreateTime)
				.list();
	}

	@Override
	public FlowConfigAgg.ConfigEntity selectConfigByFlowId(String flowId) {
		FlowConfig flowConfig = flowConfigMapper.selectById(flowId);
		return ModelUtils.convert(flowConfig, FlowConfigAgg.ConfigEntity.class);
	}

	@Override
	public String selectFlowIdByFlowCode(String flowCode) {
		FlowConfig flowConfig = ChainWrappers.lambdaQueryChain(flowConfigMapper)
				.select(FlowConfig::getFlowId)
				.eq(FlowConfig::getFlowCode, flowCode).one();
		return flowCode != null ? flowConfig.getFlowId() : null;
	}

	@Override
	public List<FlowConfigAgg.NodeEntity> selectNodesByFlowId(String flowId) {
		List<FlowConfigNode> nodes = ChainWrappers.lambdaQueryChain(flowConfigNodeMapper)
				.eq(FlowConfigNode::getFlowId, flowId)
				.orderByAsc(FlowConfigNode::getSequence, FlowConfigNode::getCreateTime)
				.list();
		return ModelUtils.convert(FlowConfigAgg.NodeEntity.class, nodes);
	}

	@Override
	public List<FlowConfigAgg.HandlerEntity> selectHandlersByFlowId(String flowId) {
		List<FlowConfigNodeHandler> handlers = flowConfigNodeHandleMapper.selectListByFlowId(flowId);
		return ModelUtils.convert(FlowConfigAgg.HandlerEntity.class, handlers);
	}

	@Override
	public boolean insert(FlowConfigAgg.ConfigEntity entity) {
		return flowConfigMapper.insert(ModelUtils.convert(entity, FlowConfig.class)) > 0;
	}

	@Override
	public boolean update(FlowConfigAgg.ConfigEntity entity) {
		return flowConfigMapper.updateById(ModelUtils.convert(entity, FlowConfig.class)) > 0;
	}

	@Override
	public boolean delete(FlowConfigAgg.ConfigEntity entity) {
		return flowConfigMapper.deleteById(entity.getFlowId()) > 0;
	}

	@Override
	public boolean insertBatchNodes(List<FlowConfigAgg.NodeEntity> entities) {
		List<FlowConfigNode> list = ModelUtils.convert(FlowConfigNode.class, entities);
		list.forEach(flowConfigNodeMapper::insert);
		return true;
	}

	@Override
	public boolean insert(FlowConfigAgg.NodeEntity entity) {
		return flowConfigNodeMapper.insert(ModelUtils.convert(entity, FlowConfigNode.class)) > 0;
	}

	@Override
	public boolean update(FlowConfigAgg.NodeEntity entity) {
		return flowConfigNodeMapper.updateById(ModelUtils.convert(entity, FlowConfigNode.class)) > 0;
	}

	@Override
	public boolean delete(FlowConfigAgg.NodeEntity entity) {
		return flowConfigNodeMapper.deleteById(entity.getFlowNodeId()) > 0;
	}

	@Override
	public boolean insertBatchHandlers(List<FlowConfigAgg.HandlerEntity> entities) {
		List<FlowConfigNodeHandler> list = ModelUtils.convert(FlowConfigNodeHandler.class, entities);
		list.forEach(flowConfigNodeHandleMapper::insert);
		return true;
	}

	@Override
	public boolean insert(FlowConfigAgg.HandlerEntity entity) {
		return flowConfigNodeHandleMapper.insert(ModelUtils.convert(entity, FlowConfigNodeHandler.class)) > 0;
	}

	@Override
	public boolean update(FlowConfigAgg.HandlerEntity entity) {
		return flowConfigNodeHandleMapper.updateById(ModelUtils.convert(entity, FlowConfigNodeHandler.class)) > 0;
	}

	@Override
	public boolean delete(FlowConfigAgg.HandlerEntity entity) {
		return flowConfigNodeHandleMapper.deleteById(entity.getId()) > 0;
	}

}
