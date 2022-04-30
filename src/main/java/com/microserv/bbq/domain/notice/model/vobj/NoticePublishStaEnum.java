package com.microserv.bbq.domain.notice.model.vobj;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息通知 发布状态
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Getter
@AllArgsConstructor
public enum NoticePublishStaEnum {

    WAIT("wait", "等待发布"),
    FAIL("fail", "发布失败"),
    SUCCESS("succ", "发布成功");
    private final String sta;
    private final String desc;
}
