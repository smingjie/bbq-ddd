package com.microserv.bbq.apis.apimodel.flow;

import com.microserv.bbq.domain.flow.agg.FlowConfigAgg;
import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
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
    private String  flowId;       //唯一id
    private String  flowCode;     //编码
    private String  flowName;     //名称
    private String  version;      //版本
    private Boolean enabled;      //启用状态
    private String  businessType; //业务类别
    private String  businessCall; //回调状态更新地址
    List<NodeDTO>   nodes;    // 节点集合

    public FlowConfigDTO fetchNodes(List<FlowConfigNodeEntity> nodeEntities, List<FlowConfigHandlerEntity> handlers) {
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

        FlowConfigMainEntity configEntity    = ModelUtils.convert(dto, FlowConfigMainEntity.class);
        List<FlowConfigNodeEntity>    nodeEntities    = new ArrayList<>();
        List<FlowConfigHandlerEntity> handlerEntities = new ArrayList<>();

        if (configEntity.getFlowId() == null) {
            configEntity.setFlowId(SequenceUtils.uuid32());
        }

        if ((dto.getNodes() != null) &&!dto.getNodes().isEmpty()) {
            dto.getNodes().forEach(o -> {
                    nodeEntities.add(ModelUtils.convert(o, FlowConfigNodeEntity.class).setFlowId(dto.getFlowId()));

                    if ((o.getHandlers() != null) &&!o.getHandlers().isEmpty()) {
                        handlerEntities.addAll(o.getHandlers()
                                                .stream()
                                                .map(e -> ModelUtils.convert(e, FlowConfigHandlerEntity.class))
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
    public static class NodeDTO {
        private String  nodeId;            //节点id
        private String  nodeType;          //节点类型，如首节点，中间节点，尾节点
        private String  nodeName;          //节点名称
        private String  nodeLastId;        //上一个节点id,当前节点为首节点时为null
        private String  nodeNextSuccessId; //下一个节点id（当前同意后跳转节点id），当前节点为尾节点时为null
        private String  nodeNextFailureId; //下一个节点id（当前驳回后跳转节点id）
        private String  successSta;        //同意时状态，如nodeName节点审核通过
        private String  failureSta;        //驳回时状态，如nodeName节点审核不通过


        // 处理人
        private List<HandlerDTO> handlers;

        public NodeDTO fetchHandlers(List<FlowConfigHandlerEntity> handlers, String nodeId) {
            if ((handlers != null) &&!handlers.isEmpty()) {
                this.handlers = handlers.stream()
                                        .filter(o -> o.getNodeId().equals(nodeId))
                                        .map(e -> ModelUtils.convert(e, HandlerDTO.class))
                                        .collect(Collectors.toList());
            }

            return this;
        }

        public static NodeDTO of(FlowConfigNodeEntity node, List<FlowConfigHandlerEntity> handlers) {
            if (node == null) {
                return null;
            }

            return ModelUtils.convert(node, NodeDTO.class).fetchHandlers(handlers, node.getNodeId());
        }
    }
}
