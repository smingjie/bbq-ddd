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
 * 数据字典表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value="SysDict对象", description="数据字典表")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId("id")
    private String id;

    @ApiModelProperty(value = "字典名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "字典类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "字典码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "字典值")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "排序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "删除标记  -1：已删除  0：正常")
    @TableField("del_flag")
    private Integer delFlag;


}
