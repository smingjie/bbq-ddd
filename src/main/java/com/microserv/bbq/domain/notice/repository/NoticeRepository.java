package com.microserv.bbq.domain.notice.repository;

import com.microserv.bbq.domain.notice.model.entity.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.entity.NoticeMsgReceiveEntity;

/**
 * 消息通知-仓储接口
 *
 * @author mingjie
 * @date 2022/4/5
 */
public interface NoticeRepository {
    // 查询
    NoticeMsgEntity findOneByMsgId(String msgId);

    // 命令
    NoticeMsgEntity saveOrUpdate(NoticeMsgEntity entity);

    NoticeMsgReceiveEntity save(NoticeMsgReceiveEntity entity);

}
