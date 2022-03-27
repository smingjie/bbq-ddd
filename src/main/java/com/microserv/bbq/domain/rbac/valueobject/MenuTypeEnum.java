package com.microserv.bbq.domain.rbac.valueobject;

import lombok.Getter;

/**
 * 菜单类型定义
 *
 * @author mingjie
 * @date 2022/3/27
 */
@Getter
public enum MenuTypeEnum {
    MENU_CONTENT(0, "目录"),
    MENU_MENU(1, "菜单"),
    MENU_BUTTON(2, "按钮"),
    ;
    private final Integer menuType;
    private final String typeName;

    MenuTypeEnum(Integer menuType, String typeName) {
        this.menuType = menuType;
        this.typeName = typeName;
    }
}
