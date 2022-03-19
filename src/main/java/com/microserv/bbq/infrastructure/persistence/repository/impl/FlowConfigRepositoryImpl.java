package com.microserv.bbq.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigNodeHandlerMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigNodeMapper;
import com.microserv.bbq.domain.model.flow.FlowConfigAgg;
import com.microserv.bbq.domain.repository.FlowConfigRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FlowConfig领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class FlowConfigRepositoryImpl implements FlowConfigRepo {

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
        return ModelUtils.convertList(nodes, FlowConfigAgg.NodeEntity.class);
    }

    @Override
    public List<FlowConfigAgg.HandlerEntity> selectHandlersByFlowId(String flowId) {
        List<FlowConfigNodeHandler> handlers = flowConfigNodeHandleMapper.selectListByFlowId(flowId);
        return ModelUtils.convertList(handlers, FlowConfigAgg.HandlerEntity.class);
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
    public boolean deleteConfigByFlowId(String flowId) {
        return flowConfigMapper.deleteById(flowId) > 0;
    }

    @Override
    public boolean insertBatchNodes(List<FlowConfigAgg.NodeEntity> entities) {
        List<FlowConfigNode> list = ModelUtils.convertList(entities, FlowConfigNode.class);
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
    public boolean deleteNodesByFlowId(String flowId) {
        Wrapper<FlowConfigNode> wrapper = Wrappers.lambdaQuery(FlowConfigNode.class)
                .eq(FlowConfigNode::getFlowId, flowId);
        return flowConfigNodeMapper.delete(wrapper) > 0;
    }

    @Override
    public boolean insertBatchHandlers(List<FlowConfigAgg.HandlerEntity> entities) {
        List<FlowConfigNodeHandler> list = ModelUtils.convertList(entities, FlowConfigNodeHandler.class);
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

    @Override
    public boolean deleteHandlersByFlowId(String flowId) {
        return flowConfigNodeHandleMapper.deleteListByFlowId(flowId) > 0;
    }

}
