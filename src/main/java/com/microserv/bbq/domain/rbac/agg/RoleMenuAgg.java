package com.microserv.bbq.domain.rbac.agg;

import com.microserv.bbq.domain.rbac.entity.MenuEntity;
import com.microserv.bbq.domain.rbac.valueobject.RoleTypeEnum;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregate;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAggregateRoot;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 聚合-角色拥有的菜单
 *
 * @author mingjie
 * @date 2022/3/27
 */
@Data
@NoArgsConstructor
@DomainAggregate
public class RoleMenuAgg implements Serializable {
    @DomainAggregateRoot
    private String roleId;
    private String roleName;
    private RoleTypeEnum roleType;
    private List<MenuEntity> menus;
}
