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

import java.time.LocalDateTime;

/**
 * <p>
 * 实例历史记录表
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("flow_ins_history")
@ApiModel(value="FlowInsHistory对象", description="实例历史记录表")
public class FlowInsHistory extends AbstractBasePO {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "历史记录id")
    @TableId("id")
    private String id;

    @ApiModelProperty(value = "工作流实例id")
    @TableField("ins_id")
    private String insId;

    @ApiModelProperty(value = "工作流实例节点id")
    @TableField("ins_node_id")
    private String insNodeId;

    @ApiModelProperty(value = "具体业务模块id")
    @TableField("module_id")
    private String moduleId;

    @ApiModelProperty(value = "记录名称")
    @TableField("record_name")
    private String recordName;

    @ApiModelProperty(value = "记录状态")
    @TableField("record_sta")
    private String recordSta;

    @ApiModelProperty(value = "记录状态名称")
    @TableField("record_sta_name")
    private String recordStaName;

    @ApiModelProperty(value = "是否执行成功")
    @TableField("succeed")
    private Integer succeed;

    @ApiModelProperty(value = "处理状态")
    @TableField("handle_status")
    private String handleStatus;

    @ApiModelProperty(value = "处理结果建议")
    @TableField("handle_suggestion")
    private String handleSuggestion;

    @ApiModelProperty(value = "处理时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    @ApiModelProperty(value = "处理者id")
    @TableField("handler_id")
    private String handlerId;

    @ApiModelProperty(value = "处理者姓名")
    @TableField("handler_name")
    private String handlerName;

    @ApiModelProperty(value = "是否删除")
    @TableField("deleted")
    private Boolean deleted;


}
