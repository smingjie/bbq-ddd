package com.microserv.bbq.domain.notice.model.vobj;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息通知类型
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Getter
@AllArgsConstructor
@DomainValueObject
public enum NoticeTypeEnum {

    AUDIT("审批通知"),
    SECURITY("安全通知"),
    SELF("个人通知");

    private final String description;
}
