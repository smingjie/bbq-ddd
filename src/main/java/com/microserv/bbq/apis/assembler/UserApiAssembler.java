package com.microserv.bbq.apis.assembler;

import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.assembler.IApiDomainAssembler;

/**
 * API层装配器-用户域
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainAssembler
public class UserApiAssembler implements IApiDomainAssembler<UserEntity> {

}
