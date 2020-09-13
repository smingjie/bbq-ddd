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
 * 菜单管理
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "菜单管理")
public class SysMenu {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "唯一Id")
	@TableId("menu_id")
	private String menuId;

	@ApiModelProperty(value = "父菜单ID，一级菜单为0")
	@TableField("parent_id")
	private String parentId;

	@ApiModelProperty(value = "菜单名称")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "菜单URL")
	@TableField("url")
	private String url;

	@ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
	@TableField("type")
	private Integer type;

	@ApiModelProperty(value = "菜单图标")
	@TableField("icon")
	private String icon;

	@ApiModelProperty(value = "排序")
	@TableField("order_num")
	private Integer orderNum;


}
