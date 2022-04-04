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
        return flowConfigAssembler.po2domain(flowConfig);
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
        FlowConfigMainEntity configMain = selectFlowConfigMainByFlowId(flowId);
        List<FlowConfigNodeEntity> configNodes = selectFlowConfigNodesByFlowId(flowId);
        List<FlowConfigHandlerEntity> configHandlers = selectFlowConfigHandlersByFlowId(flowId);
        return new FlowConfigAgg(configMain, configNodes, configHandlers);
    }

    @Override
    public FlowConfigAgg selectFlowConfigAggByFlowCode(String flowCode) {
        String flowId = selectFlowIdByFlowCode(flowCode);
        return selectFlowConfigAggByFlowId(flowId);
    }

    @Override
    public boolean insert(FlowConfigMainEntity entity) {
        FlowConfig flowConfigForCreate = flowConfigAssembler.domain2po(entity);
        return flowConfigMapper.insert(flowConfigForCreate) > 0;
    }

    @Override
    public boolean update(FlowConfigMainEntity entity) {
        FlowConfig flowConfigForUpdate = flowConfigAssembler.domain2po(entity);
        return flowConfigMapper.updateById(flowConfigForUpdate) > 0;
    }

    @Override
    public boolean delete(FlowConfigMainEntity entity) {
        return flowConfigMapper.deleteById(entity.getFlowId()) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteConfigMainByFlowId(String flowId) {
        return flowConfigMapper.deleteById(flowId) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBatchNodes(List<FlowConfigNodeEntity> entities) {
        entities.forEach(this::insert);
    }

    @Override
    public boolean insert(FlowConfigNodeEntity entity) {
        FlowConfigNode node = flowConfigAssembler.domain2po(entity);
        return flowConfigNodeMapper.insert(node) > 0;
    }

    @Override
    public boolean update(FlowConfigNodeEntity entity) {
        FlowConfigNode node = flowConfigAssembler.domain2po(entity);
        return flowConfigNodeMapper.updateById(node) > 0;
    }

    @Override
    public boolean delete(FlowConfigNodeEntity entity) {
        return flowConfigNodeMapper.deleteById(entity.getNodeId()) > 0;
    }

    @Override
    public void deleteConfigNodesByFlowId(String flowId) {
        Wrapper<FlowConfigNode> wrapper = Wrappers.lambdaQuery(FlowConfigNode.class)
                .eq(FlowConfigNode::getFlowId, flowId);
        flowConfigNodeMapper.delete(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBatchHandlers(List<FlowConfigHandlerEntity> entities) {
        entities.forEach(this::insert);
    }

    @Override
    public boolean insert(FlowConfigHandlerEntity entity) {
        FlowConfigNodeHandler handler = flowConfigAssembler.domain2po(entity);
        return flowConfigNodeHandleMapper.insert(handler) > 0;
    }

    @Override
    public boolean update(FlowConfigHandlerEntity entity) {
        FlowConfigNodeHandler handler = flowConfigAssembler.domain2po(entity);
        return flowConfigNodeHandleMapper.updateById(handler) > 0;
    }

    @Override
    public boolean delete(FlowConfigHandlerEntity entity) {
        return flowConfigNodeHandleMapper.deleteById(entity.getHandlerId()) > 0;
    }

    @Override
    public void deleteConfigHandlersByFlowId(String flowId) {
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

                this.deleteConfigNodesByFlowId(flowId);
                this.insertBatchNodes(agg.getNodes());

                this.deleteConfigHandlersByFlowId(flowId);
                this.insertBatchHandlers(agg.getHandlers());
            }
            return selectFlowConfigAggByFlowId(flowId);
        } catch (Exception e) {
            log.error("工作流部署更新失败，原因是", e);
            throw new PersistException("工作流部署或更新失败", e);
        }

    }

}
