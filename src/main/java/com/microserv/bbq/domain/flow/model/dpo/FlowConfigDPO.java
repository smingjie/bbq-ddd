package com.microserv.bbq.domain.flow.model.dpo;

import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.common.interfaces.IDomainSaveOrUpdate;
import com.microserv.bbq.domain.flow.model.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigNodeEntity;
import com.microserv.bbq.domain.flow.repository.FlowConfigRepository;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 聚合 - 工作流模板配置，第一种形式，偏数据库侧的，并列结构
 *
 * @author mingjie
 * @since 2022/03/25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FlowConfigDPO implements IDomainSaveOrUpdate<FlowConfigDPO> {
    private static FlowConfigRepository flowConfigRepo = RepositoryFactory.get(FlowConfigRepository.class);

    private FlowConfigMainEntity config;            //配置信息主记录
    private List<FlowConfigNodeEntity> nodes;       //配置节点
    private List<FlowConfigHandlerEntity> handlers; //配置节点处理人

    public FlowConfigDPO(FlowConfigMainEntity config, List<FlowConfigNodeEntity> nodes, List<FlowConfigHandlerEntity> handlers) {
        this.config = config;
        this.nodes = nodes;
        this.handlers = handlers;
    }

    @Override
    public FlowConfigDPO saveOrUpdate() {
        if (this.config == null) {
            return null;
        }
        // 事务控制放在持久化层
        FlowConfigDPO configAgg = flowConfigRepo.saveOrUpdate(this);
        if (configAgg != null) {
            ModelUtils.convert(configAgg, this);
        }
        return this;
    }

    public FlowConfigDPO2 transform2FlowConfigAgg2() {
        return FlowConfigDPO2.valueOf(this);
    }

    public static FlowConfigDPO valueOf(@NotNull FlowConfigDPO2 agg2) {
        if (agg2 == null) {
            return null;
        }
        FlowConfigMainEntity configEntity = ModelUtils.convert(agg2, FlowConfigMainEntity.class);
        List<FlowConfigNodeEntity> nodeEntities = new ArrayList<>();
        List<FlowConfigHandlerEntity> handlerEntities = new ArrayList<>();

        if (configEntity.getFlowId() == null) {
            configEntity.setFlowId(SequenceUtils.uuid32());
        }

        if (!CollectionUtils.isEmpty(agg2.getNodes())) {
            agg2.getNodes().forEach(o -> {
                nodeEntities.add(ModelUtils.convert(o, FlowConfigNodeEntity.class).setFlowId(configEntity.getFlowId()));

                if (!CollectionUtils.isEmpty(o.getHandlers())) {
                    handlerEntities.addAll(o.getHandlers()
                            .stream()
                            .map(e -> ModelUtils.convert(e, FlowConfigHandlerEntity.class).setNodeId(o.getNodeId()))
                            .collect(Collectors.toList()));
                }
            });
        }

        return new FlowConfigDPO(configEntity, nodeEntities, handlerEntities);
    }

    public static FlowConfigDPO getInstanceByFlowCode(String flowCode) {
        if (Objects.isNull(flowCode)) {
            return null;
        }
        return flowConfigRepo.selectFlowConfigAggByFlowCode(flowCode);
    }

    public static FlowConfigDPO getInstanceByFlowId(String flowId) {
        if (Objects.isNull(flowId)) {
            return null;
        }
        return flowConfigRepo.selectFlowConfigAggByFlowId(flowId);
    }

}
