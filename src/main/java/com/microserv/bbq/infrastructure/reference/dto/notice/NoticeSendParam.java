package com.microserv.bbq.infrastructure.reference.dto.notice;

import com.microserv.bbq.domain.notice.model.NoticeWayEnum;

/**
 * 消息通知发送参数
 *
 * @author mingjie
 * @date 2022/5/3
 */
public abstract class NoticeSendParam {
    /**
     * 支持的消息通知方式
     */
    public abstract NoticeWayEnum getWay();
}
