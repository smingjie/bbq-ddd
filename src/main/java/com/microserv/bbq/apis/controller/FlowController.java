package com.microserv.bbq.apis.controller;


import com.microserv.bbq.application.model.flow.FlowConfigCreateParam;
import com.microserv.bbq.application.model.flow.assembler.FlowApiAssembler;
import com.microserv.bbq.domain.flow.model.dpo.FlowConfigDPO2;
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
    public FlowConfigDPO2 getFlowConfigByFlowId(@NotNull(message = "flowId不能为空") @PathVariable String flowId) {
        return FlowConfigDPO2.getInstanceByFlowId(flowId);
    }

    @ApiOperation(value = "根据flowCode获取一个工作流的配置详情-三层嵌套")
    @GetMapping("flow-config/detail")
    public FlowConfigDPO2 getFlowConfigByFlowCode(@NotNull(message = "flowCode不能为空") @RequestParam String flowCode) {
        return FlowConfigDPO2.getInstanceByFlowCode(flowCode);
    }

    @ApiOperation(value = "部署一个工作流-三层嵌套")
    @PostMapping("flow-config")
    public FlowConfigDPO2 deployFlowConfig(@Validated @RequestBody FlowConfigCreateParam flowConfig) {
        FlowConfigDPO2 agg2 = flowConfigApiAssembler.transform2FlowConfigAgg2(flowConfig);   // 模型转换
        return agg2.saveOrUpdate();  // 执行保存
    }
}

