package com.microserv.bbq.infrastructure.persistence.assembler;

import com.microserv.bbq.domain.flow.entity.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.entity.FlowConfigNodeEntity;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.assembler.IPoDomainAssembler;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;

/**
 * 流程配置模块（域）-装配器
 *
 * @author mingjie
 * @date 2022/3/23
 */
@DomainAssembler
public class FlowConfigAssembler implements IPoDomainAssembler {

    public FlowConfigMainEntity po2domain(FlowConfig po) {
        return ModelUtils.convert(po, FlowConfigMainEntity.class);
    }

    public FlowConfigNodeEntity po2domain(FlowConfigNode po) {
        return ModelUtils.convert(po, FlowConfigNodeEntity.class);
    }

    public FlowConfigHandlerEntity po2domain(FlowConfigNodeHandler po) {
        return ModelUtils.convert(po, FlowConfigHandlerEntity.class);
    }

    public FlowConfig domain2po(FlowConfigMainEntity domain) {
        return ModelUtils.convert(domain, FlowConfig.class);
    }

    public FlowConfigNode domain2po(FlowConfigNodeEntity domain) {
        return ModelUtils.convert(domain, FlowConfigNode.class);
    }

    public FlowConfigNodeHandler domain2po(FlowConfigHandlerEntity domain) {
        return ModelUtils.convert(domain, FlowConfigNodeHandler.class);
    }
}
