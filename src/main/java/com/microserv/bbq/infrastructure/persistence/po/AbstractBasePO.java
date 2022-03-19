package com.microserv.bbq.infrastructure.persistence.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据库持久化对象的父类 (PO: Persistence Object)
 *
 * @author jockeys
 * @since 2020/4/5
 */
@Data
@Accessors(chain = true)
public abstract class AbstractBasePO {

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
