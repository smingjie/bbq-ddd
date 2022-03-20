package com.microserv.bbq.domain.user.vobj;

import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainValueObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 值对象定义-用户字典（userId,displayName)
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
@Accessors(chain = true)
@DomainValueObject
public class UserDictVObj implements Serializable {
    private String userId;
    private String displayName;
}
