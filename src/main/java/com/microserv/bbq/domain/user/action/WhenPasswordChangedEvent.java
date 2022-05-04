package com.microserv.bbq.domain.user.action;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeTypeEnum;

import java.text.MessageFormat;
import java.util.Collections;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/4
 */
public class WhenPasswordChangedEvent {

    static void doPublish(String msgId, String userId) {
        //1 消息构建
        NoticeTypeEnum msgType = NoticeTypeEnum.SECURITY;
        String msgContent = MessageFormat.format("您的用户密码正在被修改，操作时间{0},如有疑问请及时登录处理", LocalDateTimeUtil.now());
        String msgTitle = "密码修改通知";
        NoticeMsgEntity noticeMsgEntity = NoticeMsgEntity.NoticeMsgEntityBuilder
                .builder(msgId, msgType, msgContent)
                .msgTitle(msgTitle)
                .receivers(Collections.singletonList(userId))
                .build();
        //2 消息发布
        noticeMsgEntity.doPublish();
    }
}
