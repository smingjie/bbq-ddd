package com.microserv.bbq.domain.model.flow;

import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.general.toolkit.ModelConvertUtils;
import com.microserv.bbq.infrastructure.persistence.FlowConfigDao;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@Accessors(chain = true)
public class FlowConfigAgg {
    private String flowId;
    private FlowConfigEntity config;
    private List<FlowConfigNodeEntity> nodes;


    //----- public method-----//
    public FlowConfigAgg fetch() {
        if (Objects.nonNull(flowId)) {
            ModelConvertUtils.convert(
                    ApplicationUtils.getBean(FlowConfigDao.class).selectConfigs(flowId),
                    this);
        }
        return this;
    }

}
