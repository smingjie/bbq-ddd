package com.microserv.bbq.application.model.notice;

import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;
import com.microserv.bbq.domain.notice.model.NoticePublishStaEnum;
import com.microserv.bbq.domain.notice.model.NoticeReceiveInfo;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/4
 */
@Data
public class NoticeResultDTO implements Serializable {
    private String msgId;
    private NoticePublishStaEnum sta;
    private List<ReceiveResult> receiveResult;

    @Data
    public static class ReceiveResult {
        private String id;                                  //记录id
        private String receiverId;                          //接收人id
        private String receiverName;                        //接收人姓名
        private List<NoticeReceiveInfo> receiveInfoList;    //接收情况
        private Boolean success;                            //是否成功接收，有一种情况成功即成功
        private LocalDateTime createTime;

        public static ReceiveResult valueOf(NoticeMsgReceiveEntity entity) {
            ReceiveResult receiveResult = new ReceiveResult();
            receiveResult.setId(entity.getId());
            receiveResult.setReceiverId(entity.getReceiverId());
            receiveResult.setReceiverName(ApplicationUtils.getBean(UserRepository.class).selectDisplayNameByUserId(entity.getReceiverId()));
            receiveResult.setReceiveInfoList(entity.getRecvInfo());
            receiveResult.setSuccess(entity.getSuccess());
            receiveResult.setCreateTime(entity.getCreateTime());
            return receiveResult;
        }
    }

    public static NoticeResultDTO valueOf(NoticeMsgEntity noticeMsgEntity, List<NoticeMsgReceiveEntity> receiveEntities) {
        NoticeResultDTO dto = new NoticeResultDTO();
        dto.setMsgId(noticeMsgEntity.getMsgId());
        dto.setSta(noticeMsgEntity.getStatus());
        if (receiveEntities != null) {
            dto.setReceiveResult(receiveEntities.stream().map(ReceiveResult::valueOf).collect(Collectors.toList()));
        }
        return dto;
    }
}
