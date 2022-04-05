package com.microserv.bbq.domain.notice.repository;

import com.microserv.bbq.domain.notice.agg.NoticeMsgAgg;
import com.microserv.bbq.domain.notice.vobj.NoticeSendResultVObj;

/**
 * 消息通知-仓储接口
 *
 * @author mingjie
 * @date 2022/4/5
 */
public interface NoticeRepository {
    // 查询
    NoticeSendResultVObj selectSendResultByNoticeId(String noticeId);

    NoticeMsgAgg selectOneByNoticeId(String noticeId);


    // 命令
    void insert(NoticeMsgAgg agg);
}
