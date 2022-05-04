package com.microserv.bbq.domain.notice.model.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.domain.notice.model.vobj.*;
import com.microserv.bbq.domain.notice.reference.NoticeSenderFacade;
import com.microserv.bbq.domain.notice.repository.NoticeRepository;
import com.microserv.bbq.domain.user.model.part.UserContactVObj;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.infrastructure.general.common.exception.BusinessException;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.general.toolkit.AssertUtils;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.toolkit.SequenceUtils;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticeEmailSendParam;
import com.microserv.bbq.infrastructure.reference.dto.notice.NoticePhoneSendParam;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 消息通知信息实体
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Slf4j
@Accessors(chain = true)
@DomainEntity
public class NoticeMsgEntity implements IDomainMetaData {
    private String msgId;                       //消息id
    private NoticeTypeEnum msgType;             //消息类型
    private String msgTitle;                    //消息标题
    private String msgContent;                  //消息内容
    private List<String> receivers;             //接收人id集合
    private List<NoticeWayEnum> ways;           //消息通知方式，如手机邮件短信等
    private NoticePublishStaEnum status;        //消息发布状态，wait等

    private LocalDateTime createTime;          //元信息，被动查询
    private String createBy;                   //元信息，被动查询
    private LocalDateTime updateTime;          //元信息，被动查询
    private String updateBy;                   //元信息，被动查询


    private static final NoticeSenderFacade noticeSenderFacade = ApplicationUtils.getBean(NoticeSenderFacade.class);
    private static final NoticeRepository noticeRepository = ApplicationUtils.getBean(NoticeRepository.class);
    private static final UserRepository userRepository = ApplicationUtils.getBean(UserRepository.class);
    private static final ApplicationEventPublisher applicationEventPublisher = ApplicationUtils.getBean(ApplicationEventPublisher.class);

    public static NoticeMsgEntity getInstanceByMsgId(String msgId) {
        return noticeRepository.findOneByMsgId(msgId);
    }


    public static NoticeMsgEntityBuilder builder(String msgId, NoticeTypeEnum msgType, String msgContent) {
        return new NoticeMsgEntityBuilder(msgId, msgType, msgContent);
    }

    /**
     * 发布消息通知
     */
    public void doPublish() {
        doPublishBefore();
        applicationEventPublisher.publishEvent(new NoticeMsgEventObject(msgId));
    }

    private void doPublishBefore() {
        NoticeMsgEntity noticeMsgDb = getInstanceByMsgId(this.msgId);
        if (noticeMsgDb == null) {
            this.saveOrUpdate();
        } else if (NoticePublishStaEnum.SUCCESS.equals(noticeMsgDb.getStatus())) {
            throw new BusinessException("消息通知已发布成功，不可重复操作");
        } else if (NoticePublishStaEnum.FAIL.equals(noticeMsgDb.getStatus())) {
            throw new BusinessException("消息通知发布失败，请执行补偿操作");
        }
    }

    /**
     * 执行消息发送动作
     *
     * @return 接收结果集合
     */
    public List<NoticeMsgReceiveEntity> doPost() {
        AssertUtils.hasText(msgId, "消息id不能为空");
        AssertUtils.notEmpty(ways, "通知方式不能为空");
        AssertUtils.notEmpty(receivers, "接受用户不能为空");
        List<NoticeMsgReceiveEntity> receiveRecords = new ArrayList<>();
        try {
            List<UserContactVObj> contacts = userRepository.selectContactByUserIds(this.receivers);
            AssertUtils.notEmpty(contacts, "未查找到任何用户的联系方式");
            contacts.forEach(uc -> {

                List<NoticeReceiveInfo> receiveInfoList = new ArrayList<>();
                if (this.supportPhone() && uc.isEnablePhone()) {
                    NoticePhoneSendParam param = NoticePhoneSendParam.valueOf(uc.getPhone(), this.msgContent);
                    receiveInfoList.add(noticeSenderFacade.doSending(param));
                }
                if (this.supportEmail() && uc.isEnableEmail()) {
                    NoticeEmailSendParam param = NoticeEmailSendParam.valueOf(uc.getEmail(), this.msgTitle, this.msgContent);
                    receiveInfoList.add(noticeSenderFacade.doSending(param));
                }


                NoticeMsgReceiveEntity receiveRecord = noticeRepository.save(new NoticeMsgReceiveEntity()
                        .setId(SequenceUtils.uuid32())
                        .setMsgId(this.getMsgId())
                        .setReceiverId(uc.getUserId())
                        .setRecvInfo(receiveInfoList)
                        .setSuccess(receiveInfoList.stream().anyMatch(NoticeReceiveInfo::isSendOk))
                );
                if (receiveRecord != null) {
                    receiveRecords.add(receiveRecord);
                    this.setStatus(NoticePublishStaEnum.SUCCESS);
                }
            });
        } catch (Exception e) {
            this.setStatus(NoticePublishStaEnum.FAIL);
        }
        this.saveOrUpdate();
        return receiveRecords;
    }

    private boolean supportEmail() {
        return this.ways.contains(NoticeWayEnum.EMAIL);
    }

    private boolean supportPhone() {
        return this.ways.contains(NoticeWayEnum.PHONE);
    }

    private NoticeMsgEntity saveOrUpdate() {
        AssertUtils.hasText(msgId, "消息id不能为空");
        NoticeMsgEntity entity = noticeRepository.saveOrUpdate(this);
        ModelUtils.convert(entity, this);
        return this;
    }


    public static class NoticeMsgEntityBuilder extends ApplicationEvent {
        private static final String DEFAULT_USER = "admin";
        private static final String DEFAULT_TITLE = "默认通知";
        private final String msgId;                //必选
        private final NoticeTypeEnum msgType;      //必选
        private final String msgContent;           //必选
        private String msgTitle = DEFAULT_TITLE;
        private List<String> receivers = Collections.singletonList(DEFAULT_USER);
        private List<NoticeWayEnum> ways = Arrays.asList(NoticeWayEnum.values());
        private NoticePublishStaEnum status = NoticePublishStaEnum.WAIT;


        private NoticeMsgEntityBuilder(String msgId, NoticeTypeEnum msgType, String msgContent) {
            super(msgId);
            this.msgId = msgId;
            this.msgType = msgType;
            this.msgContent = msgContent;
        }


        public NoticeMsgEntityBuilder msgTitle(String msgTitle) {
            this.msgTitle = msgTitle;
            return this;
        }


        public NoticeMsgEntityBuilder receivers(List<String> receivers) {
            this.receivers = receivers;
            return this;
        }

        public NoticeMsgEntityBuilder ways(List<NoticeWayEnum> ways) {
            this.ways = ways;
            return this;
        }

        public NoticeMsgEntityBuilder status(NoticePublishStaEnum status) {
            this.status = status;
            return this;
        }

        public NoticeMsgEntity build() {
            return new NoticeMsgEntity()
                    .setMsgId(msgId)
                    .setMsgType(msgType)
                    .setMsgTitle(msgTitle)
                    .setMsgContent(msgContent)
                    .setWays(ways)
                    .setReceivers(receivers)
                    .setStatus(status)
                    ;
        }
    }


}
