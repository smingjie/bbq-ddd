package com.microserv.bbq.application.model.dict.assembler;

import com.microserv.bbq.domain.dict.model.DictEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.ddd.IApiDomainAssembler;

/**
 * API层装配器-字典域
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainAssembler
public class DictApiAssembler implements IApiDomainAssembler<DictEntity> {
}
