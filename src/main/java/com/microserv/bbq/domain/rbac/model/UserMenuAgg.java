package com.microserv.bbq.domain.rbac.model;

import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainAggregateRoot;
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
