package com.microserv.bbq.domain.dict.valueobject;

import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainValueObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
@NoArgsConstructor
@DomainValueObject
public class DictValueVObj {
    private String code;        // 字典键
    private String value;       // 字典值
    private Integer orderNum;   // 排序
}
