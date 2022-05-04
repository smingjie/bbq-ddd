package com.microserv.bbq.domain.notice.model;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainValueObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 接收情况
 *
 * @author mingjie
 * @date 2022/5/2
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@DomainValueObject
public class NoticeReceiveInfo implements Serializable {

    private String way;
    private String address;
    private boolean sendOk;
    private String remark;

}
