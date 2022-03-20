package com.microserv.bbq.infrastructure.persistence.assembler;

import cn.hutool.core.util.StrUtil;
import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.vobj.UserDictVObj;
import com.microserv.bbq.infrastructure.general.extension.assembler.IDomainAssembler;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;
import org.springframework.stereotype.Component;

/**
 * 用户装配器：专门负责模型转换
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Component
public class SysUserAssembler implements IDomainAssembler<UserEntity, SysUser> {

    /**
     * 领域模型 => 数据持久化模型
     *
     * @param domain  领域对象
     * @param poClass 目标持久化模型类名
     * @return 目标持久化对象
     */
    @Override
    public SysUser oneDomain2po(UserEntity domain, Class<SysUser> poClass) {

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
    public UserEntity onePo2domain(SysUser sysUser, Class<UserEntity> domainClass) {

        UserEntity userEntity = ModelUtils.convert(sysUser, UserEntity.class);
        if (sysUser != null) {
            userEntity.setEnabled(sysUser.getStatus());
        }
        return userEntity;
    }

    public UserDictVObj convert2UserDictVObj(SysUser sysUser) {

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
