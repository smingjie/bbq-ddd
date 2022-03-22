package com.microserv.bbq.domain.flow.entity;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 流程配置主记录
 *
 * @author mingjie
 * @date 2022/3/21
 */
@Data
public class FlowConfigEntity implements IDomainMetaData {
    private String  flowId;       //唯一id
    private String  flowCode;     //编码
    private String  flowName;     //名称
    private String  version;      //版本
    private Boolean enabled;      //启用状态
    private String  businessType; //业务类别
    private String  businessCall; //回调状态更新地址
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
