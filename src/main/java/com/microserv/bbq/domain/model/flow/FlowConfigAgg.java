package com.microserv.bbq.domain.model.flow;

import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.general.toolkit.ModelConvertUtils;
import com.microserv.bbq.infrastructure.persistence.FlowConfigDao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FlowConfigAgg extends FlowConfigEntity {
    private List<FlowConfigNodeEntity> nodes;

    public FlowConfigAgg(String flowId) {
        super(flowId);
    }
    public FlowConfigAgg(FlowConfigEntity flowConfigEntity) {
         ModelConvertUtils.convert(flowConfigEntity,this);
    }
    //----- public method-----//

    /**
     * 获取模板配置的详情信息-全部
     *
     * @return
     */
    public FlowConfigAgg fetch() {
        if (Objects.nonNull(this.getFlowId())) {
            ModelConvertUtils.convert(
                    ApplicationUtils.getBean(FlowConfigDao.class).selectConfigs(this.getFlowId()),
                    this);
        }
        return this;
    }

}
