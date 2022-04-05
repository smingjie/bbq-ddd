package com.microserv.bbq.domain.rbac.aggregrate;

import com.microserv.bbq.domain.rbac.entity.MenuEntity;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregateRoot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合-用户拥有的角色
 *
 * @author mingjie
 * @date 2022/3/27
 */
@Data
@NoArgsConstructor
@DomainAggregate
public class UserMenuAgg implements Serializable {
    @DomainAggregateRoot
    private String userId;
    private String userName;
    private List<MenuEntity> menus;
}
