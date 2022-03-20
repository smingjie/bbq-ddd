package com.microserv.bbq.domain.common.interfaces;

import java.time.LocalDateTime;

/**
 * 领域对象 元数据获取
 *
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
    LocalDateTime getCreateTime();


    /**
     * 获取更新人
     */
    String getUpdateBy();

    /**
     * 获取更新时间
     */
    LocalDateTime getUpdateTime();

}
