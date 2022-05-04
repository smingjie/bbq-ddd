package com.microserv.bbq.infrastructure.persistence.converter;

import com.microserv.bbq.domain.flow.model.FlowConfigHandlerEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigMainEntity;
import com.microserv.bbq.domain.flow.model.FlowConfigNodeEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.IPoDomainConverter;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainConverter;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfig;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNode;
import com.microserv.bbq.infrastructure.persistence.po.FlowConfigNodeHandler;

/**
 * 流程配置模块（域）-转换器
 *
 * @author mingjie
 * @date 2022/3/23
 */
@DomainConverter
public class FlowConfigConverter implements IPoDomainConverter {

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
