package com.microserv.bbq.infrastructure.reference.facade.impl.notice;

import com.microserv.bbq.domain.notice.model.vobj.NoticeReceiveInfo;
import com.microserv.bbq.domain.notice.model.vobj.NoticeWayEnum;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeEmailSendParam;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeSendParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 邮件通知发射器
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Component
@Slf4j
public class NoticeEmailSender implements NoticeSender {
    @Override
    public NoticeReceiveInfo send(NoticeSendParam noticeSendParam) {
        NoticeEmailSendParam sendParam = (NoticeEmailSendParam) noticeSendParam;
        NoticeReceiveInfo receiveInfo = new NoticeReceiveInfo().setAddress(sendParam.getEmail()).setWay(NoticeWayEnum.EMAIL.name());
        try {
            receiveInfo.setSendOk(false).setRemark("success");
            log.info("执行邮件发送程序，发送参数如下{},时间戳:{}", sendParam, LocalDateTime.now());
        } catch (Exception e) {
            receiveInfo.setSendOk(false).setRemark("发送异常" + e.getMessage());
            log.info("执行邮件发送程序异常，原因是", e);
        }
        return receiveInfo;

    }

    @Override
    public boolean support(NoticeSendParam noticeSendParam) {
        return NoticeWayEnum.EMAIL.equals(noticeSendParam.getWay());
    }

}
