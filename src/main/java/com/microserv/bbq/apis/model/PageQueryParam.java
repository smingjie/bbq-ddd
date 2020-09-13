package com.microserv.bbq.apis.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页参数
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "分页面查询参数")
public class PageQueryParam {
	@ApiModelProperty("页面容量")
	private int pageSize;
	@ApiModelProperty("当前页面，从1算起")
	private int current;
}
