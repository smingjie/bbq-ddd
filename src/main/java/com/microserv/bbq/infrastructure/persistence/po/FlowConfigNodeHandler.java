package com.microserv.bbq.infrastructure.persistence.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("flow_config_node_handler")
@ApiModel(value = "FlowConfigNodeHandler对象", description = "工作流配置的节点处理者")
public class FlowConfigNodeHandler extends AbstractBasePO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "节点处理者id")
    @TableId("handler_id")
    private String id;

    @ApiModelProperty(value = "配置节点id")
    @TableField("node_id")
    private String nodeId;

    @ApiModelProperty(value = "处理者id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "处理者名称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "是否启用")
    @TableField("enabled")
    private Boolean enabled;

}
