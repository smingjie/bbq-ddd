package com.microserv.bbq.infrastructure.persistence.po;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Data
@Slf4j
@Accessors(chain = true)
public class NoticeReceiveRecord {
    private String id;                           //记录id
    private String msgId;                        //消息id
    private String receiverId;                   //接收人id
    private String info;                         //接受情况
    private Boolean normal;                      //接受是否正常，有一种情况正常就正常
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
}

