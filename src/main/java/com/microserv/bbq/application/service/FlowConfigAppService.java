package com.microserv.bbq.application.service;

import com.microserv.bbq.apis.apimodel.common.PageQueryParam;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.ApplicationService;

/**
 * 流程配置- 应用层服务
 *
 * @author mingjie
 * @date 2022/3/27
 */
@ApplicationService
public class FlowConfigAppService {
    public Object getConfigListByPage(PageQueryParam queryParam) {
        return "分页查询结果";
    }
}
