package com.microserv.bbq.domain.model.flow;

import com.microserv.bbq.domain.model.dict.DictAgg;
import com.microserv.bbq.domain.repository.FlowConfigRepo;
import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
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
public class FlowConfigEntity {
    private String flowId;
    private String flowType;
    private String flowName;
    private String flowVersion;
    private String moduleTable;
    private String moduleKey;
    private String moduleSta;
    private Boolean isEnable;

}
