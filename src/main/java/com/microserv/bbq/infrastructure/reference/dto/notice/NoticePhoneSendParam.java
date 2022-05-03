package com.microserv.bbq.infrastructure.reference.dto.notice;

import com.microserv.bbq.domain.notice.model.vobj.NoticeWayEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class NoticePhoneSendParam extends NoticeSendParam {
    private String phone;
    private String phoneContent;

    private NoticePhoneSendParam(String phone, String phoneContent) {
        this.phone = phone;
        this.phoneContent = phoneContent;
    }

    public static NoticePhoneSendParam valueOf(String phone, String content) {
        return new NoticePhoneSendParam(phone, content);
    }


    @Override
    public NoticeWayEnum getWay() {
        return NoticeWayEnum.PHONE;
    }
}
