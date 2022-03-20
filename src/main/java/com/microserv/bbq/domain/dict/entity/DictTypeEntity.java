package com.microserv.bbq.domain.dict.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 领域模型-值对象
 * Value Object => which is called VObj in our bbq-framework
 *
 * @author jockeys
 * @since 2020/4/12
 */
@Data
@Accessors(chain = true)
public class DictTypeEntity {
    private final String type; //类型,必须
    private String name; //类型名称

    public DictTypeEntity(String type) {
        this.type = type;
    }
}
