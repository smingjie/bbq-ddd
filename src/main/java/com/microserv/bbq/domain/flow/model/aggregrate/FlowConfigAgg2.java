package com.microserv.bbq.domain.flow.model.aggregrate;

import cn.hutool.core.lang.Assert;
import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.domain.common.interfaces.IDomainSaveOrUpdate;
import com.microserv.bbq.domain.flow.model.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.model.entity.FlowConfigNodeEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAggregateRoot;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 聚合 - 工作流模板配置，第一种形式，偏向API视图侧的，三层结构
 *
 * @author mingjie
 * @since 2022/03/25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@DomainAggregate
public class FlowConfigAgg2 implements IDomainSaveOrUpdate<FlowConfigAgg2>, IDomainMetaData {
    @DomainAggregateRoot
    private String flowId;        //唯一id
    private String flowCode;      //编码
    private String flowName;      //名称
    private String version;       //版本
    private Boolean enabled;      //启用状态
    private String businessType;  //业务类别
    private String businessCall;  //回调状态更新地址
    List<NodeEntity> nodes;       // 节点集合
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    @Override
    public FlowConfigAgg2 saveOrUpdate() {
        return this
                .transform2FlowConfigAgg()
                .saveOrUpdate()
                .transform2FlowConfigAgg2();
    }

    public FlowConfigAgg transform2FlowConfigAgg() {
        Assert.notNull(this.flowId, "flowId不能为空");
        return FlowConfigAgg.valueOf(this);
    }

    public static FlowConfigAgg2 valueOf(@NotNull FlowConfigAgg agg) {
        if (agg == null) {
            return null;
        }
        FlowConfigAgg2 agg2 = ModelUtils.convert(agg.getConfig(), FlowConfigAgg2.class);
        if (!CollectionUtils.isEmpty(agg.getNodes())) {
            List<NodeEntity> nodes = agg.getNodes().stream()
                    .map(o -> NodeEntity.valueOf(o, agg.getHandlers()))
                    .collect(Collectors.toList());
            agg2.setNodes(nodes);
        }
        return agg2;
    }

    public static FlowConfigAgg2 getInstanceByFlowCode(String flowCode) {
        if (Objects.isNull(flowCode)) {
            return null;
        }
        FlowConfigAgg configAgg = FlowConfigAgg.getInstanceByFlowCode(flowCode);
        return FlowConfigAgg2.valueOf(configAgg);
    }

    public static FlowConfigAgg2 getInstanceByFlowId(String flowId) {
        if (Objects.isNull(flowId)) {
            return null;
        }
        FlowConfigAgg configAgg = FlowConfigAgg.getInstanceByFlowId(flowId);
        return FlowConfigAgg2.valueOf(configAgg);

    }


    // --------------------定义---------------

    /**
     * 实体：工作流配置节点处理人
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class HandlerEntity {
        private String handlerId;       //节点处理人id
        private String userId;          //用户id
        private String userName;        //用户名
        private Boolean enabled;        //启用禁用
    }


    /**
     * 实体：工作流配置节点
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class NodeEntity {
        private String nodeId;                   //节点id
        private String nodeType;                 //节点类型，如首节点，中间节点，尾节点
        private String nodeName;                 //节点名称
        private String nodeLastId;               //上一个节点id,当前节点为首节点时为null
        private String nodeNextSuccessId;        //下一个节点id（当前同意后跳转节点id），当前节点为尾节点时为null
        private String nodeNextFailureId;        //下一个节点id（当前驳回后跳转节点id）
        private String successSta;               //同意时状态，如nodeName节点审核通过
        private String failureSta;               //驳回时状态，如nodeName节点审核不通过
        private List<HandlerEntity> handlers;    // 处理人


        private NodeEntity fetchHandlers(List<FlowConfigHandlerEntity> handlers, String nodeId) {
            if (!CollectionUtils.isEmpty(handlers)) {
                this.handlers = handlers.stream()
                        .filter(o -> o.getNodeId().equals(nodeId))
                        .map(e -> ModelUtils.convert(e, HandlerEntity.class))
                        .collect(Collectors.toList());
            }

            return this;
        }

        public static NodeEntity valueOf(FlowConfigNodeEntity node, List<FlowConfigHandlerEntity> handlers) {
            if (node == null) {
                return null;
            }

            return ModelUtils.convert(node, NodeEntity.class).fetchHandlers(handlers, node.getNodeId());
        }
    }
}
