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
 * 用户角色
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="用户角色")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "唯一id")
    @TableId("role_id")
    private String roleId;

    @ApiModelProperty(value = "角色代码")
    @TableField("role_code")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色类型 U自定义 S系统预设")
    @TableField("role_type")
    private String roleType;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态  0：禁用  1：正常")
    @TableField("status")
    private Integer status;


}
