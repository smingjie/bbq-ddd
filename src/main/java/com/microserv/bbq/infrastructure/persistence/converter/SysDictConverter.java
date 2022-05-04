package com.microserv.bbq.infrastructure.persistence.converter;

import com.microserv.bbq.domain.dict.model.DictEntity;
import com.microserv.bbq.domain.dict.model.DictTypeEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.IPoDomainConverter;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainConverter;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysDict;

/**
 * 字典模型转换器
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainConverter
public class SysDictConverter implements IPoDomainConverter {

    public SysDict domain2po(DictEntity domain) {
        return ModelUtils.convert(domain, SysDict.class);
    }

    public DictEntity po2domain(SysDict po) {
        return ModelUtils.convert(po, DictEntity.class);
    }

    public DictTypeEntity po2domainDictTypeEntity(SysDict sysDict) {
        return sysDict == null ? null : new DictTypeEntity(sysDict.getType(), sysDict.getName());
    }
}
