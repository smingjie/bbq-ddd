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
 * 工作流实例表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_ins")
@ApiModel(value="FlowIns对象", description="工作流实例表")
public class FlowIns extends AbstractBasePo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "工作流实例id")
    @TableId("ins_id")
    private String insId;

    @ApiModelProperty(value = "工作流配置id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "相关业务模块的主键id")
    @TableField("module_id")
    private String moduleId;

    @ApiModelProperty(value = "工作流实例名称")
    @TableField("module_name")
    private String moduleName;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "是否执行完毕")
    @TableField("is_finish")
    private Integer isFinish;


}
