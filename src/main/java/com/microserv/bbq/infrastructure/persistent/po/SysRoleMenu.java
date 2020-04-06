package com.microserv.bbq.infrastructure.persistent.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.microserv.bbq.infrastructure.persistent.po.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与菜单对应关系
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role_menu")
@ApiModel(value="SysRoleMenu对象", description="角色与菜单对应关系")
public class SysRoleMenu extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一id")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private String menuId;


}
