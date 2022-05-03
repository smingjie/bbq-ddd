package com.microserv.bbq.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.notice.model.entity.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.entity.NoticeMsgReceiveEntity;
import com.microserv.bbq.domain.notice.repository.NoticeRepository;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainRepository;
import com.microserv.bbq.infrastructure.general.toolkit.AssertUtils;
import com.microserv.bbq.infrastructure.persistence.converter.NoticeConverter;
import com.microserv.bbq.infrastructure.persistence.po.NoticeMsg;
import com.microserv.bbq.infrastructure.persistence.po.NoticeReceiveRecord;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.NoticeMsgMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.NoticeReceiveRecordMapper;
import lombok.RequiredArgsConstructor;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
@DomainRepository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeConverter noticeConverter;
    private final NoticeReceiveRecordMapper noticeReceiveRecordMapper;
    private final NoticeMsgMapper noticeMsgMapper;

    @Override
    public NoticeMsgEntity findOneByMsgId(String msgId) {
        NoticeMsg noticeMsg = noticeMsgMapper.selectById(msgId);
        return noticeMsg == null ? null : noticeConverter.convert(noticeMsg);
    }

    @Override
    public NoticeMsgEntity saveOrUpdate(NoticeMsgEntity entity) {

        // 模型转换
        NoticeMsg noticeMsg = noticeConverter.convert(entity);

        // 处理
        AssertUtils.hasText(entity.getMsgId(), "消息id不能为空");
        if (existsNoticeMsg(noticeMsg.getMsgId())) {
            noticeMsgMapper.updateById(noticeMsg);
        } else {
            noticeMsgMapper.insert(noticeMsg);
        }
        // 模型转换
        return noticeConverter.convert(noticeMsgMapper.selectById(noticeMsg.getMsgId()));

    }

    public NoticeMsgReceiveEntity save(NoticeMsgReceiveEntity entity) {
        NoticeReceiveRecord record = noticeConverter.convert(entity);
        noticeReceiveRecordMapper.insert(record);
        return noticeConverter.convert(noticeReceiveRecordMapper.selectById(entity.getId()));
    }

    public boolean existsNoticeMsg(String msgId) {
        return ChainWrappers.lambdaQueryChain(noticeMsgMapper).eq(NoticeMsg::getMsgId, msgId).count() > 0;
    }
}
