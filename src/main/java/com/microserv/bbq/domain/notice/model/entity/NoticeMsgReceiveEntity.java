package com.microserv.bbq.domain.notice.model.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.domain.notice.model.vobj.NoticeReceiveInfo;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 消息通知接收记录
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Slf4j
@Accessors(chain = true)
@DomainEntity
public class NoticeMsgReceiveEntity implements IDomainMetaData {
    private String id;                           //记录id
    private String msgId;                        //消息id
    private String receiverId;                   //接收人id
    private List<NoticeReceiveInfo> recvInfo;    //接收情况
    private Boolean success;                     //是否成功接收，有一种情况成功即成功
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
}
