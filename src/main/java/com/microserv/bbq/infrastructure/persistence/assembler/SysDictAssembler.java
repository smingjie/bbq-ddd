package com.microserv.bbq.infrastructure.persistence.assembler;

import com.microserv.bbq.domain.dict.entity.DictEntity;
import com.microserv.bbq.domain.dict.entity.DictTypeEntity;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.assembler.IPoDomainAssembler;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysDict;

/**
 * 字典装配器：专门负责模型转换
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainAssembler
public class SysDictAssembler implements IPoDomainAssembler {

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
