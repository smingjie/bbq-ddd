package com.microserv.bbq.infrastructure.persistence;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.collect.Lists;
import com.microserv.bbq.domain.model.flow.FlowConfigAgg;
import com.microserv.bbq.domain.model.flow.FlowConfigEntity;
import com.microserv.bbq.domain.model.flow.FlowConfigNodeEntity;
import com.microserv.bbq.domain.model.flow.FlowConfigNodeHandleEntity;
import com.microserv.bbq.domain.repository.FlowConfigRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ModelConvertUtils;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigNodeHandleMapper;
import com.microserv.bbq.infrastructure.persistence.mapper.FlowConfigNodeMapper;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final FlowConfigNodeHandleMapper flowConfigNodeHandleMapper;

    @Override
    public FlowConfigAgg selectConfigs(String flowId) {
        // 1 查询
        FlowConfig flowConfig = flowConfigMapper.selectById(flowId);
        List<FlowConfigNode> nodes = selectConfigNodes(flowId);
        List<FlowConfigNodeHandle> handlers = flowConfigNodeHandleMapper.selectListByFlowId(flowId);
        // 2 组装
        FlowConfigEntity cfgEntity = ModelConvertUtils.convert(flowConfig, FlowConfigEntity.class);
        List<FlowConfigNodeEntity> nodeEntities = Lists.newArrayList();
        nodes.forEach(o -> {
            FlowConfigNodeEntity nodeEntity = ModelConvertUtils.convert(o, FlowConfigNodeEntity.class);
            List<FlowConfigNodeHandleEntity> handleEntities = Lists.newArrayList();
            handlers.forEach(h -> {
                if (h.getFlowNodeId().equals(o.getFlowNodeId())) {
                    handleEntities.add(ModelConvertUtils.convert(h, FlowConfigNodeHandleEntity.class));
                }
            });
            nodeEntity.setHandlers(handleEntities);
            nodeEntities.add(nodeEntity);
        });
        return new FlowConfigAgg(cfgEntity).setNodes(nodeEntities);
    }


    @Override
    public boolean insert(FlowConfigAgg data) {
        // 1 模型转换
        JSONObject jsonObject = transfer(data);
        // 2 存储
        flowConfigMapper.insert(jsonObject.getObject("config", FlowConfig.class));
        ((List<FlowConfigNode>) jsonObject.get("nodes")).forEach(flowConfigNodeMapper::insert);
        ((List<FlowConfigNodeHandle>) jsonObject.get("handlers")).forEach(flowConfigNodeHandleMapper::insert);
        return true;
    }

    @Override
    public boolean update(FlowConfigAgg data) {
        // 1 模型转换
        JSONObject jsonObject = transfer(data);
        // 2 存储
        flowConfigMapper.updateById(jsonObject.getObject("config", FlowConfig.class));
        ((List<FlowConfigNode>) jsonObject.get("nodes")).forEach(flowConfigNodeMapper::updateById);
        ((List<FlowConfigNodeHandle>) jsonObject.get("handlers")).forEach(flowConfigNodeHandleMapper::updateById);
        return true;
    }

    @Override
    public boolean delete(FlowConfigAgg data) {
        return false;
    }


    List<FlowConfigNode> selectConfigNodes(String flowId) {
        return new LambdaQueryChainWrapper<>(flowConfigNodeMapper)
                .eq(FlowConfigNode::getFlowId, flowId)
                .orderByAsc(FlowConfigNode::getSequence, FlowConfigNode::getCreateTime)
                .list();
    }

    public JSONObject transfer(FlowConfigAgg data) {
        // 1 模型转换
        FlowConfig config = ModelConvertUtils.convert(data, FlowConfig.class);
        List<FlowConfigNode> nodes = new ArrayList<>();
        List<FlowConfigNodeHandle> handlers = Lists.newArrayList();
        if (Objects.nonNull(data.getNodes()) && !data.getNodes().isEmpty()) {
            data.getNodes().forEach(o -> {
                nodes.add(ModelConvertUtils.convert(o, FlowConfigNode.class).setFlowId(data.getFlowId()));
                if (Objects.nonNull(o.getHandlers()) && !o.getHandlers().isEmpty()) {
                    o.getHandlers().forEach(h -> handlers.add(
                            ModelConvertUtils.convert(h, FlowConfigNodeHandle.class).setFlowNodeId(o.getFlowNodeId())));
                }
            });
        }

        return new JSONObject()
                .fluentPut("config", config).fluentPut("nodes", nodes).fluentPut("handlers", handlers);
    }
}
