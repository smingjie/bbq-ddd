package com.microserv.bbq.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@Accessors(chain = true)
@TableName("sys_config")
@ApiModel(value = "SysConfig对象", description = "系统配置信息表")
public class SysConfig {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "唯一ID")
	@TableId("id")
	private String id;

	@ApiModelProperty(value = "key")
	@TableField("param_key")
	private String paramKey;

	@ApiModelProperty(value = "value")
	@TableField("param_value")
	private String paramValue;

	@ApiModelProperty(value = "状态   0：隐藏   1：显示")
	@TableField("status")
	private Integer status;

	@ApiModelProperty(value = "备注")
	@TableField("remark")
	private String remark;


}
