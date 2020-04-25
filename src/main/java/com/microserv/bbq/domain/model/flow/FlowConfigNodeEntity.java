package com.microserv.bbq.domain.model.flow;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author jockeys
 * @since 2020/4/25
 */
@Data
@Accessors(chain = true)
public class FlowConfigNodeEntity {

    private String flowNodeId;
    private String flowId;
    private Integer nodeType;
    private String nodeName;
    private String nodeLastId;
    private String nodeNextId;
    private String nodeFailId;
    private Integer sequence;
    private String succSta;
    private String failSta;

    private List<FlowConfigNodeHandleEntity> handlers;
}
