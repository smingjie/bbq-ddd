package com.microserv.bbq.apis.assembler;

import com.microserv.bbq.domain.flow.entity.FlowConfigMainEntity;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.assembler.IApiDomainAssembler;

/**
 * API层装配器-流程域
 *
 * @author mingjie
 * @date 2022/3/23
 */
@DomainAssembler
public class FlowConfigApiAssembler implements IApiDomainAssembler<FlowConfigMainEntity> {
}
