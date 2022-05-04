package com.microserv.bbq.application.listener;

import com.microserv.bbq.domain.notice.model.NoticeMsgEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgReceiveEntity;
import com.microserv.bbq.domain.notice.model.NoticeMsgEventObject;
import com.microserv.bbq.domain.notice.model.NoticePublishStaEnum;
import com.microserv.bbq.infrastructure.general.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息通知事件监听器
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Slf4j
@Component
public class NoticeMsgEventListener implements ApplicationListener<NoticeMsgEventObject> {


    @Override
    public void onApplicationEvent(NoticeMsgEventObject event) {
        long beginTime = System.currentTimeMillis();
        StringBuilder logMsgBuilder = new StringBuilder();
        try {
            logMsgBuilder.append("消息通知事件监听器，已延迟2s执行。").append("参数信息:").append(event);
            List<NoticeMsgReceiveEntity> recvList = doBusiness(event.getMsgId());
            logMsgBuilder.append("投递结果:").append(recvList);
        } catch (Exception e) {
            log.error("消息通知异常，原因是", e);
        } finally {
            logMsgBuilder.append("持续时间:").append(System.currentTimeMillis() - beginTime);
            log.info(logMsgBuilder.toString());
        }
    }

    private List<NoticeMsgReceiveEntity> doBusiness(String msgId) {
        List<NoticeMsgReceiveEntity> recvList = null;
        try {
            Thread.sleep(2000); //延迟2s处理
            NoticeMsgEntity noticeMsgEntity = NoticeMsgEntity.getInstanceByMsgId(msgId);
            if (noticeMsgEntity == null) {
                throw new BusinessException("未找到消息通知记录");
            }
            if (!NoticePublishStaEnum.WAIT.equals(noticeMsgEntity.getStatus())) {
                throw new BusinessException("消息通知不是等待发布(wait)状态，无法执行");
            }
            recvList = noticeMsgEntity.doPost();

        } catch (Exception e) {
            log.error("消息通知已监听到，执行过程发生异常，原因是", e);
        }
        return recvList;
    }
}
