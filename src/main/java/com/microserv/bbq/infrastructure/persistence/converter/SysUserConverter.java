package com.microserv.bbq.infrastructure.persistence.converter;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.user.model.UserContact;
import com.microserv.bbq.domain.user.model.UserDict;
import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.infrastructure.general.extension.ddd.IPoDomainConverter;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainConverter;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;

/**
 * 用户模型转换器
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainConverter
public class SysUserConverter implements IPoDomainConverter {

    /**
     * 领域模型 => 数据持久化模型
     *
     * @param domain 领域对象
     * @return 目标持久化对象
     */
    public SysUser domain2po(UserEntity domain) {

        SysUser sysUser = ModelUtils.convert(domain, SysUser.class);
        if (sysUser != null) {
            sysUser.setStatus(domain.getEnabled());
        }
        return sysUser;
    }

    /**
     * 数据持久化模型  =>  领域模型
     *
     * @param sysUser 持久化对象
     * @return 目标领域对象
     */
    public UserEntity po2domain(SysUser sysUser) {

        UserEntity userEntity = ModelUtils.convert(sysUser, UserEntity.class);
        if (sysUser != null) {
            userEntity.setEnabled(sysUser.getStatus());
        }
        return userEntity;
    }

    public UserDict po2domainUserDictVObj(SysUser sysUser) {

        String displayName = "";
        if (StrUtil.isBlank(sysUser.getUsername())) {
            displayName = sysUser.getName();
        } else if (StrUtil.isBlank(sysUser.getName())) {
            displayName = sysUser.getUsername();
        } else {
            displayName = String.join("/", sysUser.getName(), sysUser.getUsername());
        }

        return new UserDict().setUserId(sysUser.getUserId()).setDisplayName(displayName);
    }

    public UserContact po2domainUserContactVObj(SysUser sysUser) {
        return new UserContact().setUserId(sysUser.getUserId()).setPhone(sysUser.getMobile());
    }


}
