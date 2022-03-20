package com.microserv.bbq.apis.controller;


import com.microserv.bbq.domain.flow.FlowConfigAgg;
import com.microserv.bbq.apis.apimodel.flow.FlowConfigDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 工作流-模板配置
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@RestController
public class FlowCfgController {

	@ApiOperation(value = "获取一个工作流的配置详情-三层嵌套")
	@GetMapping("flow-cfg/{flowId}/detail")
	public FlowConfigDTO getFlowConfigById(@PathVariable String flowId) {
		return FlowConfigDTO.regroup(new FlowConfigAgg().getById(flowId));
	}

	@ApiOperation(value = "部署一个工作流-三层嵌套")
	@PostMapping("flow-cfg")
	public FlowConfigAgg deployFlowConfig(@RequestBody FlowConfigDTO flowConfigDTO) {
		return FlowConfigDTO.transform(flowConfigDTO).saveOrUpdate();
	}
}

