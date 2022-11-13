package com.microserv.bbq.domain.rbac.model.dpo;

import com.microserv.bbq.domain.rbac.model.MenuEntity;
import com.microserv.bbq.domain.rbac.model.RoleTypeEnum;
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
public class RoleMenuDPO implements Serializable {
    private String roleId;
    private String roleName;
    private RoleTypeEnum roleType;
    private List<MenuEntity> menus;
}
