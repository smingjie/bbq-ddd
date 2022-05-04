package com.microserv.bbq.infrastructure.persistence.converter;

import com.microserv.bbq.domain.rbac.model.MenuEntity;
import com.microserv.bbq.domain.rbac.model.RoleEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.IPoDomainConverter;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainConverter;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysMenu;
import com.microserv.bbq.infrastructure.persistence.po.SysRole;

/**
 * RBAC模块（域）-转换器
 *
 * @author mingjie
 * @date 2022/4/4
 */
@DomainConverter
public class RbacConverter implements IPoDomainConverter {

    public MenuEntity po2domain(SysMenu po) {
        return ModelUtils.convert(po, MenuEntity.class);
    }

    public RoleEntity po2domain(SysRole po) {
        return ModelUtils.convert(po, RoleEntity.class);
    }

    public SysMenu domain2po(MenuEntity domain) {
        return ModelUtils.convert(domain, SysMenu.class);
    }

    public SysRole domain2po(RoleEntity domain) {
        return ModelUtils.convert(domain, SysRole.class);
    }
}
