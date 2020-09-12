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
 * 用户与角色关系表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value = "SysUserRole对象", description = "用户与角色关系表")
public class SysUserRole {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "唯一id")
	@TableId("id")
	private String id;

	@ApiModelProperty(value = "角色id")
	@TableField("role_id")
	private String roleId;

	@ApiModelProperty(value = "用户id")
	@TableField("user_id")
	private String userId;


}
