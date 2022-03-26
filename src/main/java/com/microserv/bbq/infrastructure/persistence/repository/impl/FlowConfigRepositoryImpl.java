package com.microserv.bbq.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.flow.agg.FlowConfigAgg;
import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
import com.microserv.bbq.domain.flow.repository.FlowConfigRepository;
import com.microserv.bbq.infrastructure.general.exception.PersistException;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.assembler.FlowConfigAssembler;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigNodeHandlerMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.FlowConfigNodeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * FlowConfig领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class FlowConfigRepositoryImpl implements FlowConfigRepository {

    private final FlowConfigMapper flowConfigMapper;
    private final FlowConfigNodeMapper flowConfigNodeMapper;
    private final FlowConfigNodeHandlerMapper flowConfigNodeHandleMapper;
    private final FlowConfigAssembler flowConfigAssembler;

    List<FlowConfigNode> selectConfigNodes(String flowId) {
        return ChainWrappers.lambdaQueryChain(flowConfigNodeMapper)
                .eq(FlowConfigNode::getFlowId, flowId)
                .orderByAsc(FlowConfigNode::getSequence, FlowConfigNode::getCreateTime)
                .list();
    }

    @Override
    public FlowConfigMainEntity selectFlowConfigMainByFlowId(String flowId) {
        FlowConfig flowConfig = flowConfigMapper.selectById(flowId);
        return flowConfigAssembler.po2domain(flowConfig, FlowConfigMainEntity.class);
    }

    @Override
    public String selectFlowIdByFlowCode(String flowCode) {
        FlowConfig flowConfig = ChainWrappers.lambdaQueryChain(flowConfigMapper)
                .select(FlowConfig::getFlowId)
                .eq(FlowConfig::getFlowCode, flowCode).one();
        return flowCode != null ? flowConfig.getFlowId() : null;
    }

    @Override
    public List<FlowConfigNodeEntity> selectFlowConfigNodesByFlowId(String flowId) {
        List<FlowConfigNode> nodes = ChainWrappers.lambdaQueryChain(flowConfigNodeMapper)
                .eq(FlowConfigNode::getFlowId, flowId)
                .orderByAsc(FlowConfigNode::getSequence, FlowConfigNode::getCreateTime)
                .list();

        return nodes.stream().map(flowConfigAssembler::po2domain).collect(Collectors.toList());
    }

    @Override
    public List<FlowConfigHandlerEntity> selectFlowConfigHandlersByFlowId(String flowId) {
        List<FlowConfigNodeHandler> handlers = flowConfigNodeHandleMapper.selectListByFlowId(flowId);
        return handlers.stream().map(flowConfigAssembler::po2domain).collect(Collectors.toList());
    }

    @Override
    public FlowConfigAgg selectFlowConfigAggByFlowId(String flowId) {
        return null;
    }

    @Override
    public boolean insert(FlowConfigMainEntity entity) {
        return flowConfigMapper.insert(ModelUtils.convert(entity, FlowConfig.class)) > 0;
    }

    @Override
    public boolean update(FlowConfigMainEntity entity) {
        return flowConfigMapper.updateById(ModelUtils.convert(entity, FlowConfig.class)) > 0;
    }

    @Override
    public boolean delete(FlowConfigMainEntity entity) {
        return flowConfigMapper.deleteById(entity.getFlowId()) > 0;
    }

    @Override
    public boolean deleteConfigByFlowId(String flowId) {
        return flowConfigMapper.deleteById(flowId) > 0;
    }

    @Override
    public void insertBatchNodes(List<FlowConfigNodeEntity> entities) {
        List<FlowConfigNode> list = ModelUtils.convertList(entities, FlowConfigNode.class);
        list.forEach(flowConfigNodeMapper::insert);
    }

    @Override
    public boolean insert(FlowConfigNodeEntity entity) {
        return flowConfigNodeMapper.insert(ModelUtils.convert(entity, FlowConfigNode.class)) > 0;
    }

    @Override
    public boolean update(FlowConfigNodeEntity entity) {
        return flowConfigNodeMapper.updateById(ModelUtils.convert(entity, FlowConfigNode.class)) > 0;
    }

    @Override
    public boolean delete(FlowConfigNodeEntity entity) {
        return flowConfigNodeMapper.deleteById(entity.getNodeId()) > 0;
    }

    @Override
    public void deleteNodesByFlowId(String flowId) {
        Wrapper<FlowConfigNode> wrapper = Wrappers.lambdaQuery(FlowConfigNode.class)
                .eq(FlowConfigNode::getFlowId, flowId);
        flowConfigNodeMapper.delete(wrapper);
    }

    @Override
    public void insertBatchHandlers(List<FlowConfigHandlerEntity> entities) {
        List<FlowConfigNodeHandler> list = ModelUtils.convertList(entities, FlowConfigNodeHandler.class);
        list.forEach(flowConfigNodeHandleMapper::insert);
    }

    @Override
    public boolean insert(FlowConfigHandlerEntity entity) {
        return flowConfigNodeHandleMapper.insert(ModelUtils.convert(entity, FlowConfigNodeHandler.class)) > 0;
    }

    @Override
    public boolean update(FlowConfigHandlerEntity entity) {
        return flowConfigNodeHandleMapper.updateById(ModelUtils.convert(entity, FlowConfigNodeHandler.class)) > 0;
    }

    @Override
    public boolean delete(FlowConfigHandlerEntity entity) {
        return flowConfigNodeHandleMapper.deleteById(entity.getHandlerId()) > 0;
    }

    @Override
    public void deleteHandlersByFlowId(String flowId) {
        flowConfigNodeHandleMapper.deleteListByFlowId(flowId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlowConfigAgg saveOrUpdate(FlowConfigAgg agg) {

        try {
            String flowId = agg.getConfig().getFlowId();
            FlowConfigMainEntity configMainEntity = selectFlowConfigMainByFlowId(flowId);

            if (configMainEntity == null) {    // 新增
                this.insert(agg.getConfig());
                this.insertBatchNodes(agg.getNodes());
                this.insertBatchHandlers(agg.getHandlers());

            } else {
                this.update(agg.getConfig());

                this.deleteNodesByFlowId(flowId);
                this.insertBatchNodes(agg.getNodes());

                this.deleteHandlersByFlowId(flowId);
                this.insertBatchHandlers(agg.getHandlers());
            }
            return selectFlowConfigAggByFlowId(flowId);
        } catch (Exception e) {
            throw new PersistException("工作流部署或更新失败", e);
        }

    }

}
