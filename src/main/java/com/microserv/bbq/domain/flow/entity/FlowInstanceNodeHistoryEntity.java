package com.microserv.bbq.domain.flow.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 流程实例节点记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
public class FlowInstanceNodeHistoryEntity implements IDomainMetaData {
    private String instanceHistoryId;        //实例记录id
    private String instanceId;               //实例id
    private String instanceNodeId;           //实例节点id
    private Boolean agreed;                  //是否同意
    private String suggestion;               //建议
    private String businessType;             //业务类别
    private String businessSta;              //业务状态
    private String businessStaName;          //业务状态名称
    private String createBy;
    private LocalDateTime createTime;

    /**
     * 获取更新人
     */
    @Override
    public String getUpdateBy() {
        return null;
    }

    /**
     * 获取更新时间
     */
    @Override
    public LocalDateTime getUpdateTime() {
        return null;
    }
}
