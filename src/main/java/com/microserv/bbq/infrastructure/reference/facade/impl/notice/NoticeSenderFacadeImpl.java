package com.microserv.bbq.infrastructure.reference.facade.impl.notice;

import com.microserv.bbq.domain.notice.model.NoticeReceiveInfo;
import com.microserv.bbq.domain.notice.reference.NoticeSenderFacade;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeSendParam;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Component
public class NoticeSenderFacadeImpl implements NoticeSenderFacade {

    private final List<NoticeSender> noticeSenders;

    NoticeSenderFacadeImpl(NoticeEmailSender noticeEmailSender, NoticePhoneSender noticePhoneSender) {
        noticeSenders = Arrays.asList(noticeEmailSender, noticePhoneSender);
    }

    /**
     * 发送消息
     *
     * @param sendParam 发送消息参数
     * @return 投递结果
     */
    @Override
    public NoticeReceiveInfo doSend(NoticeSendParam sendParam) {
        for (NoticeSender noticeSender : noticeSenders) {
            if (noticeSender.support(sendParam)) {
                return noticeSender.send(sendParam);
            }
        }
        return null;
    }
}
