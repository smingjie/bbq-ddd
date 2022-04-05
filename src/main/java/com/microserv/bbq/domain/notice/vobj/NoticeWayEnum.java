package com.microserv.bbq.domain.notice.vobj;

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
public enum NoticeWayEnum {

    PHONE("phone", "手机短信"),
    EMAIL("email", "邮件"),
    WECHAT("wechat", "微信");

    private final String code;
    private final String value;
}
