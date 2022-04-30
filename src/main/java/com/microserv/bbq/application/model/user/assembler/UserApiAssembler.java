package com.microserv.bbq.application.model.user.assembler;

import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.ddd.IApiDomainAssembler;

/**
 * API层装配器-用户域
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainAssembler
public class UserApiAssembler implements IApiDomainAssembler<UserEntity> {

}
