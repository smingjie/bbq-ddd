package com.microserv.bbq.domain.flow.model;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 流程实例主记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@DomainEntity
public class FlowInstanceEntity implements IDomainMetaData {
    private String instanceId;       //实例id
    private String flowId;           //流程配置id
    private String businessType;     //业务类别
    private String businessCall;     //业务回调状态更新地址
    private String instanceName;     //名称
    private String finished;         //是否完成
    private Boolean faceKilled;      //是否强制终止

    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
