package com.microserv.bbq.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.microserv.bbq.infrastructure.persistence.common.AbstractBasePO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工作流实例的节点处理者
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_ins_node_handler")
@ApiModel(value="FlowInsNodeHandler对象", description="工作流实例的节点处理者")
public class FlowInsNodeHandler extends AbstractBasePO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "节点处理者id")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "实例节点id")
    @TableField("ins_node_id")
    private String insNodeId;

    @ApiModelProperty(value = "处理者id")
    @TableField("handler_id")
    private String handlerId;

    @ApiModelProperty(value = "处理者名称")
    @TableField("handler_name")
    private String handlerName;

    @ApiModelProperty(value = "是否启用")
    @TableField("enabled")
    private Boolean enabled;
}
