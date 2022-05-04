package com.microserv.bbq.infrastructure.reference.dto.notice;

import com.microserv.bbq.domain.notice.model.NoticeWayEnum;
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
public class NoticeEmailSendParam extends NoticeSendParam {
    private String email;
    private String title;
    private String emailContent;

    private NoticeEmailSendParam(String email, String title, String emailContent) {
        this.email = email;
        this.title = title;
        this.emailContent = emailContent;
    }

    public static NoticeEmailSendParam valueOf(String email, String title, String emailContent) {
        return new NoticeEmailSendParam(email, title, emailContent);
    }


    @Override
    public NoticeWayEnum getWay() {
        return NoticeWayEnum.EMAIL;
    }
}
