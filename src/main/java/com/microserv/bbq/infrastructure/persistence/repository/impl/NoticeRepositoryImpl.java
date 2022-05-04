package com.microserv.bbq.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;
import com.microserv.bbq.domain.notice.repository.NoticeRepository;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainRepository;
import com.microserv.bbq.infrastructure.general.toolkit.AssertUtils;
import com.microserv.bbq.infrastructure.persistence.converter.NoticeConverter;
import com.microserv.bbq.infrastructure.persistence.po.NoticeMsg;
import com.microserv.bbq.infrastructure.persistence.po.NoticeReceiveRecord;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.NoticeMsgMapper;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.NoticeReceiveRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public NoticeMsgEntity findNoticeMsgByMsgId(String msgId) {
        NoticeMsg noticeMsg = noticeMsgMapper.selectById(msgId);
        return noticeConverter.convert(noticeMsg);
    }

    @Override
    public NoticeMsgReceiveEntity findNoticeMsgReceiveById(String id) {
        NoticeReceiveRecord noticeReceiveRecord = noticeReceiveRecordMapper.selectById(id);
        return noticeConverter.convert(noticeReceiveRecord);
    }

    @Override
    public NoticeMsgReceiveEntity findNoticeMsgReceiveByMsgIdAndReceiverId(String msgId, String receiverId) {
        NoticeReceiveRecord noticeReceiveRecord = ChainWrappers.lambdaQueryChain(noticeReceiveRecordMapper)
                .eq(NoticeReceiveRecord::getMsgId, msgId)
                .eq(NoticeReceiveRecord::getReceiverId, receiverId)
                .one();
        return noticeConverter.convert(noticeReceiveRecord);
    }

    @Override
    public List<NoticeMsgReceiveEntity> findNoticeMsgReceiveListByMsgId(String msgId) {
        List<NoticeReceiveRecord> list = ChainWrappers.lambdaQueryChain(noticeReceiveRecordMapper)
                .eq(NoticeReceiveRecord::getMsgId, msgId)
                .list();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list.stream().map(noticeConverter::convert).collect(Collectors.toList());
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

    @Override
    public NoticeMsgReceiveEntity save(NoticeMsgReceiveEntity entity) {
        NoticeReceiveRecord record = noticeConverter.convert(entity);
        AssertUtils.hasText(entity.getMsgId(), "消息id不能为空");
        noticeReceiveRecordMapper.insert(record);
        return noticeConverter.convert(noticeReceiveRecordMapper.selectById(entity.getId()));
    }

    @Override
    public boolean existsNoticeMsg(String msgId) {
        return ChainWrappers.lambdaQueryChain(noticeMsgMapper).eq(NoticeMsg::getMsgId, msgId).count() > 0;
    }
}
