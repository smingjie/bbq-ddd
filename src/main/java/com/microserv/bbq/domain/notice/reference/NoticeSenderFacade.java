package com.microserv.bbq.domain.notice.reference;

import com.microserv.bbq.domain.notice.model.vobj.NoticeReceiveInfo;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeSendParam;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
public interface NoticeSenderFacade {

    /**
     * 发送消息
     *
     * @param sendParam 发送消息参数
     * @return 投递结果
     */
    NoticeReceiveInfo doSending(NoticeSendParam sendParam);
}
