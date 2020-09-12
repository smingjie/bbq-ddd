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
public class FlowIns extends AbstractBasePO {

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

    @ApiModelProperty(value = "业务模块的类别")
    @TableField("module_type")
    private String moduleType;

    @ApiModelProperty(value = "相关业务模块的主表名")
    @TableField("module_table")
    private String moduleTable;

    @ApiModelProperty(value = "相关业务模块的主表的主键列名")
    @TableField("module_key")
    private String moduleKey;

    @ApiModelProperty(value = "相关业务模块的主表的状态列名")
    @TableField("module_sta")
    private String moduleSta;

    @ApiModelProperty(value = "工作流实例名称")
    @TableField("module_name")
    private String moduleName;

    @ApiModelProperty(value = "当前节点")
    @TableField("node_curr")
    private String nodeCurr;

    @ApiModelProperty(value = "上一个节点")
    @TableField("node_pre")
    private String nodePre;

    @ApiModelProperty(value = "下一个节点")
    @TableField("node_next")
    private String nodeNext;

    @ApiModelProperty(value = "是否执行成功")
    @TableField("succeed")
    private Boolean succeed;

    @ApiModelProperty(value = "是否删除")
    @TableField("deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "是否执行完毕")
    @TableField("finished")
    private Boolean finished;


}
