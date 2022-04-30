package com.microserv.bbq.domain.notice.model.vobj;

import lombok.Data;

import java.util.List;

/**
 * 消息通知 发送结果统计
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
public class NoticeSendResultVObj {
    private String receiverId;                        //接收人id
    private List<ReceiveWayAndResult> receiveResult;  //接收人接受方式及送达情况
    private boolean success;                          //送达成功，只要有一种成功即认为成功


    @Data
    public static class ReceiveWayAndResult {
        private NoticeTypeEnum receiveWay;
        private boolean receiveSuccess;
    }
}
