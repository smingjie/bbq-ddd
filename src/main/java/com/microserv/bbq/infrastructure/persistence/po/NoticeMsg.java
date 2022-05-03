package com.microserv.bbq.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 消息通知信息表
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Data
public class NoticeMsg implements Serializable {
    @TableId
    private String msgId;                       //消息id
    private String type;                        //消息类型
    private String title;                       //消息标题
    private String content;                     //消息内容
    private String receivers;                   //接收人id集合
    private String ways;                        //消息通知方式，如手机邮件短信等
    private String status;                      //消息发布状态，wait等
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}