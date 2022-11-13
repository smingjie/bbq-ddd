package com.microserv.bbq.application.model.flow.assembler;

import com.microserv.bbq.application.model.flow.FlowConfigCreateParam;
import com.microserv.bbq.domain.flow.model.dpo.FlowConfigDPO2;
import com.microserv.bbq.domain.flow.model.FlowConfigMainEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.ddd.IApiDomainAssembler;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * API层装配器-流程域
 *
 * @author mingjie
 * @date 2022/3/23
 */
@DomainAssembler
public class FlowApiAssembler implements IApiDomainAssembler<FlowConfigMainEntity> {
    private static final String CONFIG_FLOW_ID_PREFIX = "cfg";
    private static final String CONFIG_HANDLER_ID_PREFIX = "handler";

    public FlowConfigDPO2 transform2FlowConfigAgg2(FlowConfigCreateParam configCreateParam) {

        FlowConfigDPO2 agg2 = new FlowConfigDPO2();
        ModelUtils.convert(configCreateParam, agg2);
        if (StringUtils.isEmpty(configCreateParam.getFlowId())) {
            agg2.setFlowId(SequenceUtils.timestampNo(CONFIG_FLOW_ID_PREFIX));
        }
        if (!CollectionUtils.isEmpty(agg2.getNodes())) {
            for (FlowConfigDPO2.NodeEntity nodeEntity : agg2.getNodes()) {
                if (!CollectionUtils.isEmpty(nodeEntity.getHandlers())) {
                    nodeEntity.getHandlers().forEach(handler -> {
                        if (StringUtils.isEmpty(handler.getHandlerId())) {
                            handler.setHandlerId(SequenceUtils.timestampNo(CONFIG_HANDLER_ID_PREFIX));
                        }
                    });
                }
            }
        }
        return agg2;
    }


}
