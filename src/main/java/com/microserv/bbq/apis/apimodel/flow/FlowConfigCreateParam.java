package com.microserv.bbq.apis.apimodel.flow;

import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流程模板配置-创建参数封装
 *
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@ApiModel
@NoArgsConstructor
@Accessors(chain = true)
public class FlowConfigCreateParam {

    @ApiModelProperty(value = "模板配置唯一id，系统自动生成", hidden = true)
    private String flowId;

    @NotNull(message = "流程模板配置编码不能为空")
    @ApiModelProperty(value = "模板配置编码", required = true)
    private String flowCode;

    @NotNull(message = "流程模板配置名称不能为空")
    @ApiModelProperty(value = "模板配置名称", required = true)
    private String flowName;

    @NotNull(message = "版本号不能为空")
    @ApiModelProperty(value = "版本号", required = true)
    private String version;

    @ApiModelProperty(value = "启用状态")
    private Boolean enabled;

    @NotNull(message = "业务类别不能为空")
    @ApiModelProperty(value = "业务类别", required = true)
    private String businessType;

    @ApiModelProperty(value = "回调状态更新地址")
    private String businessCall;

    @ApiModelProperty(value = "节点集合")
    List<NodeParam> nodes;


    // --------------------定义---------------

    /**
     * 实体：工作流配置节点处理人
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class HandlerParam {
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
    public static class NodeParam {
        private String nodeId;             //节点id
        private String nodeType;           //节点类型，如首节点，中间节点，尾节点
        private String nodeName;           //节点名称
        private String nodeLastId;         //上一个节点id,当前节点为首节点时为null
        private String nodeNextSuccessId;  //下一个节点id（当前同意后跳转节点id），当前节点为尾节点时为null
        private String nodeNextFailureId;  //下一个节点id（当前驳回后跳转节点id）
        private String successSta;         //同意时状态，如nodeName节点审核通过
        private String failureSta;         //驳回时状态，如nodeName节点审核不通过
        private List<HandlerParam> handlers;// 处理人


        public NodeParam fetchHandlers(List<FlowConfigHandlerEntity> handlers, String nodeId) {
            if ((handlers != null) && !handlers.isEmpty()) {
                this.handlers = handlers.stream()
                        .filter(o -> o.getNodeId().equals(nodeId))
                        .map(e -> ModelUtils.convert(e, HandlerParam.class))
                        .collect(Collectors.toList());
            }

            return this;
        }

        public static NodeParam of(FlowConfigNodeEntity node, List<FlowConfigHandlerEntity> handlers) {
            if (node == null) {
                return null;
            }

            return ModelUtils.convert(node, NodeParam.class).fetchHandlers(handlers, node.getNodeId());
        }
    }
}
