package com.microserv.bbq.domain.rbac.model;

import lombok.Getter;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/3/27
 */
@Getter
public enum RoleTypeEnum {
    ROLE_SYSTEM("S", "系统预设角色"),
    ROLE_SELF_DEF("U", "用户自定义角色"),
    ;
    private final String roleType;
    private final String typeName;

    RoleTypeEnum(String roleType, String typeName) {
        this.roleType = roleType;
        this.typeName = typeName;
    }
}
