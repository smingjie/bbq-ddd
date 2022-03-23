package com.microserv.bbq.domain.flow.agg;

import com.microserv.bbq.domain.common.factory.RepositoryFactory;
import com.microserv.bbq.domain.common.interfaces.IDomainSaveOrUpdate;
import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
import com.microserv.bbq.domain.flow.repository.FlowConfigRepository;
import com.microserv.bbq.infrastructure.general.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregateRoot;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/25
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

    @Override
    public FlowConfigAgg saveOrUpdate() {
        if (this.config == null) {
            return null;
        }
        try {
            FlowConfigMainEntity configEntity = flowConfigRepo.selectFlowConfigMainByFlowId(this.config.getFlowId());

            if (configEntity == null) {    // 新增
                flowConfigRepo.insert(this.config);
                flowConfigRepo.insertBatchNodes(this.nodes);
                flowConfigRepo.insertBatchHandlers(this.handlers);

            } else {
                flowConfigRepo.update(this.config);
                flowConfigRepo.deleteNodesByFlowId(this.config.getFlowId());
                flowConfigRepo.insertBatchNodes(this.nodes);
                flowConfigRepo.deleteHandlersByFlowId(this.config.getFlowId());
                flowConfigRepo.insertBatchHandlers(this.handlers);
            }
            return this;
        } catch (Exception e) {
            throw new BusinessException("工作流部署或更新失败", e);
        }
    }

    public FlowConfigAgg getByCode(String flowCode) {
        if (Objects.nonNull(flowCode)) {
            getById(flowConfigRepo.selectFlowIdByFlowCode(flowCode));
        }

        return this;
    }

    public FlowConfigAgg getById(String flowId) {
        if (Objects.nonNull(flowId)) {
            this.config = flowConfigRepo.selectFlowConfigMainByFlowId(flowId);
            this.nodes = flowConfigRepo.selectFlowConfigNodesByFlowId(flowId);
            this.handlers = flowConfigRepo.selectFlowConfigHandlersByFlowId(flowId);
        }

        return this;
    }
}
