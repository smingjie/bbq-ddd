package com.microserv.bbq.infrastructure.general.extension.assembler;

import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;

import java.util.List;

/**
 * 领域模型装配器：实现领域模型和持久化模型之间的转换
 *
 * @param <DO> domain object
 * @param <PO> persist object
 * @author mingjie
 * @date 2022/3/20
 */
public interface IPoDomainAssembler<DO, PO> {


    /**
     * 领域模型 => 数据持久化模型
     *
     * @param domain  领域对象
     * @param poClass 目标持久化模型类名
     * @return 目标持久化对象
     */
    default PO domain2po(DO domain, Class<PO> poClass) {
        if(domain==null){
            return null;
        }
        return ModelUtils.convert(domain, poClass);
    }

    /**
     * 数据持久化模型  =>  领域模型
     *
     * @param po          持久化对象
     * @param domainClass 目标领域模型类名
     * @return 目标领域对象
     */
    default DO po2domain(PO po, Class<DO> domainClass) {
        if(po==null){
            return null;
        }
        return ModelUtils.convert(po, domainClass);
    }

    /**
     * 领域模型集合 => 数据持久化模型集合
     *
     * @param domainList 领域模型对象集合
     * @param poClass    目标持久化对象类名
     * @return 目标持久化对象集合
     */
    default List<PO> domain2po(List<DO> domainList, Class<PO> poClass) {
        return ModelUtils.convertList(domainList, poClass);
    }

    /**
     * 数据持久化模型集合  =>  领域模型集合
     *
     * @param poList      持久化对象集合
     * @param domainClass 目标领域模型类名
     * @return 目标领域模型集合
     */
    default List<DO> po2domain(List<PO> poList, Class<DO> domainClass) {
        return ModelUtils.convertList(poList, domainClass);
    }
}
