package com.microserv.bbq.apis.model.flow;

import com.microserv.bbq.domain.model.flow.FlowConfigAgg;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class FlowConfigDTO {
    private String  flowId;
    private String  flowCode;
    private String  flowType;
    private String  flowName;
    private String  flowVersion;
    private Boolean enabled;
    private String  moduleType;
    private String  moduleTable;
    private String  moduleKey;
    private String  moduleSta;
    List<NodeDTO>   nodes;    // 节点集合

    public FlowConfigDTO fetchNodes(List<FlowConfigAgg.NodeEntity> nodeEntities, List<FlowConfigAgg.HandlerEntity> handlers) {
        if ((nodeEntities != null) &&!nodeEntities.isEmpty()) {
            this.nodes = nodeEntities.stream().map(o -> NodeDTO.of(o, handlers)).collect(Collectors.toList());
        }

        return this;
    }

    /**
     * 重组三层数据
     *
     * @return
     */
    public static FlowConfigDTO regroup(@NotNull FlowConfigAgg agg) {
        return (agg == null)
               ? null
               : ModelUtils.convert(agg.getConfig(), FlowConfigDTO.class).fetchNodes(agg.getNodes(), agg.getHandlers());
    }

    /**
     * 重组并列数据
     *
     * @return
     */
    public static FlowConfigAgg transform(@NotNull FlowConfigDTO dto) {
        if (dto == null) {
            return null;
        }

        FlowConfigAgg.ConfigEntity configEntity    = ModelUtils.convert(dto, FlowConfigAgg.ConfigEntity.class);
        List<FlowConfigAgg.NodeEntity>    nodeEntities    = new ArrayList<>();
        List<FlowConfigAgg.HandlerEntity> handlerEntities = new ArrayList<>();

        if (configEntity.getFlowId() == null) {
            configEntity.setFlowId(SequenceUtils.uuid32());
        }

        if ((dto.getNodes() != null) &&!dto.getNodes().isEmpty()) {
            dto.getNodes().forEach(o -> {
                    nodeEntities.add(ModelUtils.convert(o, FlowConfigAgg.NodeEntity.class).setFlowId(dto.getFlowId()));

                    if ((o.getHandlers() != null) &&!o.getHandlers().isEmpty()) {
                        handlerEntities.addAll(o.getHandlers()
                                                .stream()
                                                .map(e -> ModelUtils.convert(e, FlowConfigAgg.HandlerEntity.class))
                                                .collect(Collectors.toList()));
                    }
                });
        }

        return new FlowConfigAgg().setConfig(configEntity).setNodes(nodeEntities).setHandlers(handlerEntities);
    }

    // --------------------定义---------------

    /**
     * 实体：工作流配置节点处理人
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class HandlerDTO {
        private String  id;
        private String  handlerId;
        private String  handlerName;
        private Boolean enabled;
    }


    /**
     * 实体：工作流配置节点
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class NodeDTO {
        private String  flowNodeId;
        private Integer nodeType;
        private String  nodeName;
        private String  nodeLastId;
        private String  nodeNextId;
        private String  nodeFailId;
        private Integer sequence;
        private String  succSta;
        private String  failSta;

        // 处理人
        private List<HandlerDTO> handlers;

        public NodeDTO fetchHandlers(List<FlowConfigAgg.HandlerEntity> handlers, String nodeId) {
            if ((handlers != null) &&!handlers.isEmpty()) {
                this.handlers = handlers.stream()
                                        .filter(o -> o.getFlowNodeId().equals(nodeId))
                                        .map(e -> ModelUtils.convert(e, HandlerDTO.class))
                                        .collect(Collectors.toList());
            }

            return this;
        }

        public static NodeDTO of(FlowConfigAgg.NodeEntity node, List<FlowConfigAgg.HandlerEntity> handlers) {
            if (node == null) {
                return null;
            }

            return ModelUtils.convert(node, NodeDTO.class).fetchHandlers(handlers, node.getFlowNodeId());
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
