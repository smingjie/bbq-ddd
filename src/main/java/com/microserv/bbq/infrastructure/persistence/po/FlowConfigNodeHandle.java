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
 * 工作流配置的节点处理者
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_config_node_handle")
@ApiModel(value="FlowConfigNodeHandle对象", description="工作流配置的节点处理者")
public class FlowConfigNodeHandle extends AbstractBasePo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "节点处理者id")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "配置节点id")
    @TableField("flow_node_id")
    private String flowNodeId;

    @ApiModelProperty(value = "是否为组类型 0个人 1角色组")
    @TableField("is_group")
    private Integer isGroup;

    @ApiModelProperty(value = "处理者id")
    @TableField("handle_id")
    private String handleId;

    @ApiModelProperty(value = "处理者名称")
    @TableField("handle_name")
    private String handleName;

    @ApiModelProperty(value = "是否启用")
    @TableField("is_enable")
    private Integer isEnable;


}
