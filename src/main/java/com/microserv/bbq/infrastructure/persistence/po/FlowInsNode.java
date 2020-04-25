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
 * 工作流实例的节点信息
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_ins_node")
@ApiModel(value="FlowInsNode对象", description="工作流实例的节点信息")
public class FlowInsNode extends AbstractBasePo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "实例节点id")
    @TableId("ins_node_id")
    private String insNodeId;

    @ApiModelProperty(value = "工作流实例id")
    @TableField("ins_id")
    private String insId;

    @ApiModelProperty(value = "实例节点类型，0首节点 1中间节点 2尾结点")
    @TableField("node_type")
    private Integer nodeType;

    @ApiModelProperty(value = "工作流节点名称")
    @TableField("node_name")
    private String nodeName;

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

    @ApiModelProperty(value = "是否已经执行")
    @TableField("is_execute")
    private Integer isExecute;

    @ApiModelProperty(value = "是否执行成功")
    @TableField("is_success")
    private Integer isSuccess;


}
