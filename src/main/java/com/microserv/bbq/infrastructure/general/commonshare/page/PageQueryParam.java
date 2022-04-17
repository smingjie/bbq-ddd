package com.microserv.bbq.infrastructure.general.commonshare.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页参数
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "分页面查询参数")
public class PageQueryParam implements Serializable {

    @NotNull(message = "页面容量pageSize不能为空")
    @Min(value = 1, message = "页面容量pageSize不能小于1")
    @ApiModelProperty("页面容量")
    private int pageSize;

    @NotNull(message = "当前页面current不能为空")
    @Min(value = 1, message = "当前页面current不能小于1")
    @ApiModelProperty("当前页面，从1算起")
    private int current;
}
