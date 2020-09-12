package com.microserv.bbq.apis.controller;


import com.microserv.bbq.apis.model.FlowConfigDTO;
import com.microserv.bbq.domain.model.flow.FlowConfigAgg;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工作流-模板配置
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@RestController
public class FlowConfigController {

	@ApiOperation(value = "获取一个工作流的配置详情-三层嵌套")
	@GetMapping("flow-config/{flowId}/detail")
	public FlowConfigDTO getFlowConfigById(@PathVariable String flowId) {
		return FlowConfigDTO.regroup(new FlowConfigAgg().getById(flowId));
	}
}

