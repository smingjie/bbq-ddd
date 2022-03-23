package com.microserv.bbq.infrastructure.persistence.assembler;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.vobj.UserDictVObj;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainAssembler;
import com.microserv.bbq.infrastructure.general.extension.assembler.IPoDomainAssembler;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;

/**
 * 用户装配器：专门负责模型转换
 *
 * @author mingjie
 * @date 2022/3/20
 */
@DomainAssembler
public class SysUserAssembler implements IPoDomainAssembler<UserEntity, SysUser> {

    /**
     * 领域模型 => 数据持久化模型
     *
     * @param domain  领域对象
     * @param poClass 目标持久化模型类名
     * @return 目标持久化对象
     */
    @Override
    public SysUser domain2po(UserEntity domain, Class<SysUser> poClass) {

        SysUser sysUser = ModelUtils.convert(domain, poClass);
        if (sysUser != null) {
            sysUser.setStatus(domain.getEnabled());
        }
        return sysUser;
    }


    /**
     * 数据持久化模型  =>  领域模型
     *
     * @param sysUser     持久化对象
     * @param domainClass 目标领域模型类名
     * @return 目标领域对象
     */
    @Override
    public UserEntity po2domain(SysUser sysUser, Class<UserEntity> domainClass) {

        UserEntity userEntity = ModelUtils.convert(sysUser, UserEntity.class);
        if (sysUser != null) {
            userEntity.setEnabled(sysUser.getStatus());
        }
        return userEntity;
    }

    public UserDictVObj po2domainUserDictVObj(SysUser sysUser) {

        String displayName = "";
        if (StrUtil.isBlank(sysUser.getUsername())) {
            displayName = sysUser.getName();
        } else if (StrUtil.isBlank(sysUser.getName())) {
            displayName = sysUser.getUsername();
        } else {
            displayName = String.join("/", sysUser.getName(), sysUser.getUsername());
        }

        return new UserDictVObj().setUserId(sysUser.getUserId()).setDisplayName(displayName);
    }


}
