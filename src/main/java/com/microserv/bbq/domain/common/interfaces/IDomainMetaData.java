package com.microserv.bbq.domain.common.interfaces;

/**
 * @author mingjie
 * @date 2022/3/19
 */
public interface IDomainMetaData {
    /**
     * 获取创建人
     */
    String getCreateBy();

    /**
     * 获取创建时间戳
     */
    String getCreateTime();


    /**
     * 获取更新人
     */
    String getUpdateBy();

    /**
     * 获取更新时间
     */
    String getUpdateTime();

}
