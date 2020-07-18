package com.microserv.bbq.domain.model.flow;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class FlowConfigEntity {
    private String flowId;
    private String flowType;
    private String flowName;
    private String flowVersion;
    private String moduleTable;
    private String moduleKey;
    private String moduleSta;
    private Boolean isEnable;

    public FlowConfigEntity(String flowId) {
        this.flowId = flowId;
    }
}
