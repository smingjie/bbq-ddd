package com.microserv.bbq.domain.model.dict;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jockeys
 * @since 2020/4/12
 */
@Data
@Accessors(chain = true)
public class DictTypeVo {
    private String type;
    private String name;

    public DictTypeVo(String type) {
        this.type = type;
    }
}
