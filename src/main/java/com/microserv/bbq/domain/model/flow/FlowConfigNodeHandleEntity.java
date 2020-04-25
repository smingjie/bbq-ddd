package com.microserv.bbq.domain.model.flow;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@Accessors(chain = true)
public class FlowConfigNodeHandleEntity {
    private String id;
    private String flowNodeId;
    private Integer isGroup;
    private String handleId;
    private String handleName;
    private Boolean isEnable;
}
