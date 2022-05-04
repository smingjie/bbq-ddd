package com.microserv.bbq.application.service;

import com.microserv.bbq.application.model.notice.NoticeResultDTO;
import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/4
 */
@Slf4j
@DomainService
public class NoticeApplicationService {


    public NoticeResultDTO getReceiveResult(String msgId) {
        NoticeMsgEntity noticeMsgEntity = NoticeMsgEntity.getInstanceByMsgId(msgId);
        List<NoticeMsgReceiveEntity> noticeMsgReceiveEntities = noticeMsgEntity.getReceiveResult();
        return NoticeResultDTO.valueOf(noticeMsgEntity, noticeMsgReceiveEntities);
    }
}
