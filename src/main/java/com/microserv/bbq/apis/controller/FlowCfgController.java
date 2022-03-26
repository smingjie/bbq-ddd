package com.microserv.bbq.apis.controller;


import com.microserv.bbq.domain.flow.agg.FlowConfigAgg2;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * 工作流-模板配置
 *
 * @author jockeys
 * @date 2022-03-25
 */
@RestController
public class FlowCfgController {

    @ApiOperation(value = "获取一个工作流的配置详情-三层嵌套")
    @GetMapping("flow-cfg/{flowId}/detail")
    public FlowConfigAgg2 getFlowConfigById(@PathVariable String flowId) {
        return new FlowConfigAgg2().getById(flowId);
    }

    @ApiOperation(value = "部署一个工作流-三层嵌套")
    @PostMapping("flow-cfg")
    public FlowConfigAgg2 deployFlowConfig(@RequestBody FlowConfigAgg2 flowConfigAgg2) {
        Assert.notNull(flowConfigAgg2, "flowConfigAgg2不允许为空");
        return flowConfigAgg2.saveOrUpdate();

    }
}

