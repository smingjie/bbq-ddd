package com.microserv.bbq.domain.model.flow;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.FlowConfigRepo;
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
public class FlowConfigAgg {

    /**
     * 配置信息
     */
    private ConfigEntity config;

    /**
     * 配置节点
     */
    private List<NodeEntity> nodes;

    /**
     * 节点处理人
     */
    private List<HandlerEntity> handlers;

    public FlowConfigAgg getByCode(String flowCode) {
        if (Objects.nonNull(flowCode)) {
            getById(RepoFactory.get(FlowConfigRepo.class).selectFlowIdByFlowCode(flowCode));
        }

        return this;
    }

    public FlowConfigAgg getById(String flowId) {
        if (Objects.nonNull(flowId)) {
            this.config   = RepoFactory.get(FlowConfigRepo.class).selectConfigByFlowId(flowId);
            this.nodes    = RepoFactory.get(FlowConfigRepo.class).selectNodesByFlowId(flowId);
            this.handlers = RepoFactory.get(FlowConfigRepo.class).selectHandlersByFlowId(flowId);
        }

        return this;
    }

    // --------------------定义---------------
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class ConfigEntity {
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
    }


    /**
     * 实体：工作流配置节点处理人
     */
    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class HandlerEntity {
        private String  id;
        private String  flowNodeId;
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
    public static class NodeEntity {
        private String  flowNodeId;
        private String  flowId;
        private Integer nodeType;
        private String  nodeName;
        private String  nodeLastId;
        private String  nodeNextId;
        private String  nodeFailId;
        private Integer sequence;
        private String  succSta;
        private String  failSta;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
