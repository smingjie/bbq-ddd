package com.microserv.bbq.domain.flow.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 流程配置-节点处理人记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FlowConfigHandlerEntity implements IDomainMetaData {

    private String handlerId;       //节点处理人id
    private String nodeId;          //节点id
    private String userId;          //用户id
    private String userName;        //用户名
    private Boolean enabled;        //启用禁用
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
