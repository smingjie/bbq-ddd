package com.microserv.bbq.apis.controller;


import com.microserv.bbq.apis.apimodel.flow.FlowConfigCreateParam;
import com.microserv.bbq.apis.assembler.FlowApiAssembler;
import com.microserv.bbq.domain.flow.aggregrate.FlowConfigAgg2;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * 工作流
 *
 * @author mingjie
 * @date 2022-03-25
 */
@RestController
@RequiredArgsConstructor
public class FlowController {
    private final FlowApiAssembler flowConfigApiAssembler;

    @ApiOperation(value = "根据flowId获取一个工作流的配置详情-三层嵌套")
    @GetMapping("flow-config/{flowId}/detail")
    public FlowConfigAgg2 getFlowConfigById(@NotNull(message = "flowId不能为空") @PathVariable String flowId) {
        return FlowConfigAgg2.getById(flowId);
    }

    @ApiOperation(value = "根据flowCode获取一个工作流的配置详情-三层嵌套")
    @GetMapping("flow-config/detail")
    public FlowConfigAgg2 getFlowConfigByCode(@NotNull(message = "flowCode不能为空") @RequestParam String flowCode) {
        return FlowConfigAgg2.getByCode(flowCode);
    }

    @ApiOperation(value = "部署一个工作流-三层嵌套")
    @PostMapping("flow-config")
    public FlowConfigAgg2 deployFlowConfig(@Validated @RequestBody FlowConfigCreateParam flowConfig) {
        // 模型转换
        FlowConfigAgg2 agg2 = flowConfigApiAssembler.transform2FlowConfigAgg2(flowConfig);
        // 执行保存
        return agg2.saveOrUpdate();
    }
}

