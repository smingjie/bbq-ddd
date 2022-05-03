package com.microserv.bbq.domain.notice.model.vobj;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Getter
@AllArgsConstructor
@DomainValueObject
public enum NoticeWayEnum {

    PHONE("手机短信"),
    EMAIL("邮件");

    private final String desc;
}
