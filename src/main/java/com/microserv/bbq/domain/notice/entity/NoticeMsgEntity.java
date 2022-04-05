package com.microserv.bbq.domain.notice.entity;

import com.microserv.bbq.domain.notice.vobj.NoticeTypeEnum;
import com.microserv.bbq.domain.notice.vobj.NoticeWayEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 消息通知信息实体
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Slf4j
public class NoticeMsgEntity {
    private String id;                       //消息id
    private NoticeTypeEnum type;             //消息类型，如审批，消费，个人安全等
    private String title;                    //消息标题
    private String content;                  //消息内容
    private List<NoticeWayEnum> ways;        //通知方式，如手机,邮件，微信
    private String publisher;                //发布人id
    private List<String> receivers;          //接收人id集合
    private String publishSta;               //消息发布状态，wait等


    public static class Actions {
        public void publish() {
            //执行发布操作
        }
    }
}
