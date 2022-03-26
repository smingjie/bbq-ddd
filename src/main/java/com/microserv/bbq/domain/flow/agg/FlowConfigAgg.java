package com.microserv.bbq.domain.flow.agg;

import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.common.interfaces.IDomainSaveOrUpdate;
import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
import com.microserv.bbq.domain.flow.repository.FlowConfigRepository;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregateRoot;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@DomainAggregate
public class FlowConfigAgg implements IDomainSaveOrUpdate<FlowConfigAgg> {
    private static FlowConfigRepository flowConfigRepo = RepositoryFactory.get(FlowConfigRepository.class);

    @DomainAggregateRoot
    private FlowConfigMainEntity config;            //配置信息主记录
    private List<FlowConfigNodeEntity> nodes;       //配置节点
    private List<FlowConfigHandlerEntity> handlers; //配置节点处理人

    public FlowConfigAgg(FlowConfigMainEntity config,
                         List<FlowConfigNodeEntity> nodes,
                         List<FlowConfigHandlerEntity> handlers) {
        this.config = config;
        this.nodes = nodes;
        this.handlers = handlers;
    }

    @Override
    public FlowConfigAgg saveOrUpdate() {
        if (this.config == null) {
            return null;
        }
        // 事务控制放在持久化层
        FlowConfigAgg configAgg = flowConfigRepo.saveOrUpdate(this);
        if (configAgg != null) {
            ModelUtils.convert(configAgg, this);
        }
        return this;
    }

    public FlowConfigAgg getByCode(String flowCode) {
        if (Objects.nonNull(flowCode)) {
            getById(flowConfigRepo.selectFlowIdByFlowCode(flowCode));
        }

        return this;
    }

    public FlowConfigAgg getById(String flowId) {
        if (Objects.nonNull(flowId)) {
            FlowConfigAgg configAgg = flowConfigRepo.selectFlowConfigAggByFlowId(flowId);
            if (configAgg != null) {
                ModelUtils.convert(configAgg, this);
            }
        }

        return this;
    }


    /**
     * 重组并列数据
     */
    public static FlowConfigAgg valueOf(@NotNull FlowConfigAgg2 agg2) {
        if (agg2 == null) {
            return null;
        }

        FlowConfigMainEntity configEntity = ModelUtils.convert(agg2, FlowConfigMainEntity.class);
        List<FlowConfigNodeEntity> nodeEntities = new ArrayList<>();
        List<FlowConfigHandlerEntity> handlerEntities = new ArrayList<>();

        if (configEntity.getFlowId() == null) {
            configEntity.setFlowId(SequenceUtils.uuid32());
        }

        if ((agg2.getNodes() != null) && !agg2.getNodes().isEmpty()) {
            agg2.getNodes().forEach(o -> {
                nodeEntities.add(ModelUtils.convert(o, FlowConfigNodeEntity.class).setFlowId(agg2.getFlowId()));

                if ((o.getHandlers() != null) && !o.getHandlers().isEmpty()) {
                    handlerEntities.addAll(o.getHandlers()
                            .stream()
                            .map(e -> ModelUtils.convert(e, FlowConfigHandlerEntity.class))
                            .collect(Collectors.toList()));
                }
            });
        }

        return new FlowConfigAgg(configEntity, nodeEntities, handlerEntities);
    }

}
