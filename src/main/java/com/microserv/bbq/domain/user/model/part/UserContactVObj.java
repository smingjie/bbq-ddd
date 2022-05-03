package com.microserv.bbq.domain.user.model.part;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainValueObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 通知用户的联系方式
 *
 * @author mingjie
 * @date 2022/5/1
 */
@Data
@Accessors(chain = true)
@DomainValueObject
public class UserContactVObj implements Serializable {
    private String userId;
    private String phone;
    private boolean enablePhone = true; //是否开启手机接收
    private String email;
    private boolean enableEmail = false;//是否开启邮件接收
}
