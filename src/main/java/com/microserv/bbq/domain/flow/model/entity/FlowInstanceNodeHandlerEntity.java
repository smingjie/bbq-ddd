package com.microserv.bbq.domain.flow.model.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 流程实例节点处理人记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@DomainEntity
public class FlowInstanceNodeHandlerEntity implements IDomainMetaData {

    private String handlerId;       //节点处理人id
    private String instanceNodeId;  //节点id
    private String userId;          //用户id
    private String userName;        //用户名
    private Boolean enabled;        //启用禁用
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
