package com.microserv.bbq.domain.dict.model;

import com.microserv.bbq.domain.common.interfaces.IDomainMetaData;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DictItemEntity extends DictValue implements IDomainMetaData {
    private String id;          // 唯一id
    private String remark;      // 备注
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

    public DictItemEntity(DictEntity dictEntity) {
        if (dictEntity != null) {
            ModelUtils.convert(dictEntity, this);
        }
    }
}
