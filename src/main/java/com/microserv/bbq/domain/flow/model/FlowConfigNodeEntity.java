package com.microserv.bbq.domain.flow.model;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 流程配置-节点记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@DomainEntity
public class FlowConfigNodeEntity implements IDomainMetaData {
    private String  nodeId;            //节点id
    private String  flowId;            //所属流程
    private String  nodeType;          //节点类型，如首节点，中间节点，尾节点
    private String  nodeName;          //节点名称
    private String  nodeLastId;        //上一个节点id,当前节点为首节点时为null
    private String  nodeNextSuccessId; //下一个节点id（当前同意后跳转节点id），当前节点为尾节点时为null
    private String  nodeNextFailureId; //下一个节点id（当前驳回后跳转节点id）
    private String  successSta;        //同意时状态，如nodeName节点审核通过
    private String  failureSta;        //驳回时状态，如nodeName节点审核不通过
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
