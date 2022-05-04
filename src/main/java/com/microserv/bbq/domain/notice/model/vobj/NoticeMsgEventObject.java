package com.microserv.bbq.domain.notice.model.vobj;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEvent;
import org.springframework.context.ApplicationEvent;

/**
 * 通知事件监听对象
 *
 * @author mingjie
 * @date 2022/5/2
 */
@DomainEvent
public class NoticeMsgEventObject extends ApplicationEvent {


    public NoticeMsgEventObject(String msgId) {
        super(msgId);
    }

    public String getMsgId() {
        return (String) getSource();
    }


}
