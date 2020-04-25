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
 * 工作流配置的节点信息
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_config_node")
@ApiModel(value="FlowConfigNode对象", description="工作流配置的节点信息")
public class FlowConfigNode extends AbstractBasePo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "配置节点id")
    @TableId("flow_node_id")
    private String flowNodeId;

    @ApiModelProperty(value = "工作流配置id")
    @TableField("flow_id")
    private String flowId;

    @ApiModelProperty(value = "配置节点类型，0首节点 1中间节点 2尾结点")
    @TableField("node_type")
    private Integer nodeType;

    @ApiModelProperty(value = "工作流节点名称")
    @TableField("node_name")
    private String nodeName;

    @ApiModelProperty(value = "工作流上一个节点（0为首节点）")
    @TableField("node_last_id")
    private String nodeLastId;

    @ApiModelProperty(value = "工作流下一个节点（if success）")
    @TableField("node_next_id")
    private String nodeNextId;

    @ApiModelProperty(value = "工作流失败跳转节点")
    @TableField("node_fail_id")
    private String nodeFailId;

    @ApiModelProperty(value = "排序")
    @TableField("sequence")
    private Integer sequence;

    @ApiModelProperty(value = "成功状态码")
    @TableField("succ_sta")
    private String succSta;

    @ApiModelProperty(value = "失败状态码")
    @TableField("fail_sta")
    private String failSta;


}
