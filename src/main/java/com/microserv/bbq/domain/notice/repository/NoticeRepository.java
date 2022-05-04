package com.microserv.bbq.domain.notice.repository;

import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;

import java.util.List;

/**
 * 消息通知-仓储接口
 *
 * @author mingjie
 * @date 2022/4/5
 */
public interface NoticeRepository {
    // 查询
    NoticeMsgEntity findNoticeMsgByMsgId(String msgId);
    boolean existsNoticeMsg(String msgId);

    NoticeMsgReceiveEntity findNoticeMsgReceiveById(String id);
    NoticeMsgReceiveEntity findNoticeMsgReceiveByMsgIdAndReceiverId(String msgId, String receiverId);
    List<NoticeMsgReceiveEntity> findNoticeMsgReceiveListByMsgId(String msgId);

    // 命令
    NoticeMsgEntity saveOrUpdate(NoticeMsgEntity entity);

    NoticeMsgReceiveEntity save(NoticeMsgReceiveEntity entity);

}
