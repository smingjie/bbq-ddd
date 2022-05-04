package com.microserv.bbq.infrastructure.persistence.converter;

import com.alibaba.fastjson.JSON;
import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;
import com.microserv.bbq.domain.notice.model.NoticePublishStaEnum;
import com.microserv.bbq.domain.notice.model.NoticeReceiveInfo;
import com.microserv.bbq.domain.notice.model.NoticeTypeEnum;
import com.microserv.bbq.domain.notice.model.NoticeWayEnum;
import com.microserv.bbq.infrastructure.general.extension.ddd.IPoDomainConverter;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainConverter;
import com.microserv.bbq.infrastructure.persistence.po.NoticeMsg;
import com.microserv.bbq.infrastructure.persistence.po.NoticeReceiveRecord;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
@DomainConverter
public class NoticeConverter implements IPoDomainConverter {


    public NoticeMsgEntity convert(NoticeMsg po) {
        if (po == null) {
            return null;
        }
        NoticeMsgEntity noticeMsgEntity = new NoticeMsgEntity();
        noticeMsgEntity.setMsgId(po.getMsgId());
        noticeMsgEntity.setMsgType(NoticeTypeEnum.valueOf(po.getType()));
        noticeMsgEntity.setMsgTitle(po.getTitle());
        noticeMsgEntity.setMsgContent(po.getContent());
        noticeMsgEntity.setReceivers(Stream.of(po.getReceivers().split(";")).collect(Collectors.toList()));
        noticeMsgEntity.setWays(Stream.of(po.getWays().split(";")).map(NoticeWayEnum::valueOf).collect(Collectors.toList()));
        noticeMsgEntity.setStatus(NoticePublishStaEnum.parseOf(po.getStatus()));
        noticeMsgEntity.setCreateTime(po.getCreateTime());
        noticeMsgEntity.setCreateBy(po.getCreateBy());
        noticeMsgEntity.setUpdateTime(po.getUpdateTime());
        noticeMsgEntity.setUpdateBy(po.getUpdateBy());
        return noticeMsgEntity;
    }

    public NoticeMsg convert(NoticeMsgEntity entity) {
        if (entity == null) {
            return null;
        }
        NoticeMsg noticeMsgDb = new NoticeMsg();
        noticeMsgDb.setMsgId(entity.getMsgId());
        noticeMsgDb.setType(entity.getMsgType().name());
        noticeMsgDb.setTitle(entity.getMsgTitle());
        noticeMsgDb.setContent(entity.getMsgContent());
        noticeMsgDb.setReceivers(String.join(";", entity.getReceivers()));
        noticeMsgDb.setWays(entity.getWays().stream().map(NoticeWayEnum::name).collect(Collectors.joining(";")));
        noticeMsgDb.setStatus(entity.getStatus().getSta());
        noticeMsgDb.setCreateTime(entity.getCreateTime());
        noticeMsgDb.setCreateBy(entity.getCreateBy());
        noticeMsgDb.setUpdateTime(entity.getUpdateTime());
        noticeMsgDb.setUpdateBy(entity.getUpdateBy());
        return noticeMsgDb;
    }


    public NoticeReceiveRecord convert(NoticeMsgReceiveEntity entity) {
        if (entity == null) {
            return null;
        }
        NoticeReceiveRecord noticeReceiveRecord = new NoticeReceiveRecord();
        noticeReceiveRecord.setId(entity.getId());
        noticeReceiveRecord.setMsgId(entity.getMsgId());
        noticeReceiveRecord.setReceiverId(entity.getReceiverId());
        noticeReceiveRecord.setInfo(JSON.toJSONString(entity.getRecvInfo()));
        noticeReceiveRecord.setNormal(entity.getSuccess());
        noticeReceiveRecord.setCreateTime(LocalDateTime.now());
        noticeReceiveRecord.setCreateBy(entity.getCreateBy());
        noticeReceiveRecord.setUpdateTime(LocalDateTime.now());
        noticeReceiveRecord.setUpdateBy(entity.getUpdateBy());
        return noticeReceiveRecord;
    }


    public NoticeMsgReceiveEntity convert(NoticeReceiveRecord po) {
        if (po == null) {
            return null;
        }
        NoticeMsgReceiveEntity noticeMsgReceiveEntity = new NoticeMsgReceiveEntity();
        noticeMsgReceiveEntity.setId(po.getId());
        noticeMsgReceiveEntity.setMsgId(po.getMsgId());
        noticeMsgReceiveEntity.setReceiverId(po.getReceiverId());
        noticeMsgReceiveEntity.setRecvInfo(JSON.parseArray(po.getInfo(), NoticeReceiveInfo.class));
        noticeMsgReceiveEntity.setSuccess(po.getNormal());
        noticeMsgReceiveEntity.setCreateTime(po.getCreateTime());
        noticeMsgReceiveEntity.setCreateBy(po.getCreateBy());
        noticeMsgReceiveEntity.setUpdateTime(po.getUpdateTime());
        noticeMsgReceiveEntity.setUpdateBy(po.getUpdateBy());

        return noticeMsgReceiveEntity;
    }
}
