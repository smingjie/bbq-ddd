package com.microserv.bbq.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.microserv.bbq.infrastructure.persistence.common.AbstractBasePo;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工作流配置表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_config")
@ApiModel(value="FlowConfig对象", description="工作流配置表")
public class FlowConfig extends AbstractBasePo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "工作流配置id")
    @TableId("flow_id")
    private String flowId;

    @ApiModelProperty(value = "工作流配置类型")
    @TableField("flow_type")
    private String flowType;

    @ApiModelProperty(value = "工作流配置名称")
    @TableField("flow_name")
    private String flowName;

    @ApiModelProperty(value = "工作流配置版本，1.0")
    @TableField("flow_version")
    private String flowVersion;

    @ApiModelProperty(value = "相关业务模块的主表名")
    @TableField("module_table")
    private String moduleTable;

    @ApiModelProperty(value = "相关业务模块的主表的主键列名")
    @TableField("module_key")
    private String moduleKey;

    @ApiModelProperty(value = "相关业务模块的主表的状态列名")
    @TableField("module_sta")
    private String moduleSta;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enable")
    private Integer isEnable;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Integer isDelete;


}
