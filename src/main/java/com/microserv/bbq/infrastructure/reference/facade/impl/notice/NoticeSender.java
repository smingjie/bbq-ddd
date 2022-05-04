package com.microserv.bbq.infrastructure.reference.facade.impl.notice;

import com.microserv.bbq.domain.notice.model.NoticeReceiveInfo;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeSendParam;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/3
 */
public interface NoticeSender {
    NoticeReceiveInfo send(NoticeSendParam noticeSendParam);

    boolean support(NoticeSendParam noticeSendParam);
}
