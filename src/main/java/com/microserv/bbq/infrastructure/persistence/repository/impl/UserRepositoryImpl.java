package com.microserv.bbq.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.domain.user.vobj.UserDictVObj;
import com.microserv.bbq.infrastructure.general.exception.PersistException;
import com.microserv.bbq.infrastructure.general.extension.annotation.ddd.DomainRepository;
import com.microserv.bbq.infrastructure.general.security.SecurityContext;
import com.microserv.bbq.infrastructure.persistence.assembler.SysUserAssembler;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@DomainRepository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final SysUserMapper sysUserMapper;         // 用户Mapper
    private final SysUserAssembler sysUserAssembler;   // 用户Assembler

    @Override
    public boolean delete(UserEntity item) {
        if (Objects.isNull(item) || StrUtil.isBlank(item.getUserId())) {
            throw new PersistException("删除实体时，用户id不能为空");
        }
        return sysUserMapper.deleteById(item.getUserId()) > 0;
    }

    @Override
    public boolean isMatchUsernamePassword(String username, String password) {
        if (!StrUtil.isAllNotBlank(username, password)) {
            throw new PersistException("判断用户匹配密码时，用户名和用户密码均不能为空");
        }
        return ChainWrappers.lambdaQueryChain(sysUserMapper)
                .eq(SysUser::getUsername, username).eq(SysUser::getPassword, password)
                .count() > 0;
    }

    @Override
    public boolean insert(UserEntity item) {
        if (Objects.isNull(item)) {
            throw new PersistException("保存插入实体时，用户实体不能为空");
        }

        SysUser sysUser = sysUserAssembler.domain2po(item, SysUser.class);
        return sysUserMapper.insert(sysUser) > 0;
    }

    @Override
    public UserEntity selectById(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        return sysUserAssembler.po2domain(sysUser, UserEntity.class);
    }

    @Override
    public UserEntity selectByUsername(String username) {
        SysUser sysUser = ChainWrappers.lambdaQueryChain(sysUserMapper)
                .eq(SysUser::getUsername, username)
                .one();
        return sysUserAssembler.po2domain(sysUser, UserEntity.class);

    }

    @Override
    public List<UserDictVObj> searchDictBy(String searchKey) {
        List<SysUser> userList = ChainWrappers.lambdaQueryChain(sysUserMapper)
                .select(SysUser::getUserId, SysUser::getUsername, SysUser::getName)
                .like(SysUser::getUsername, searchKey)
                .like(SysUser::getName, searchKey)
                .list();

        return CollectionUtils.isEmpty(userList)
                ? Collections.emptyList() :
                userList.stream().map(sysUserAssembler::po2domainUserDictVObj).collect(Collectors.toList());
    }

    @Override
    public boolean update(UserEntity item) {
        // 1 更新前校验
        if (Objects.isNull(item)) {
            throw new PersistException("更新实体时，用户实体不能为空");
        }
        if (StrUtil.isAllBlank(item.getUserId(), item.getUsername())) {
            throw new PersistException("更新实体时，用户id和username不能同时为空");
        }


        // 2 模型转换
        SysUser updatePO = sysUserAssembler.domain2po(item, SysUser.class);
        updatePO.setCreateBy(SecurityContext.tryGetLoginUserId());
        updatePO.setUpdateTime(LocalDateTime.now());


        // 3 更新，优先根据userId，若是没有则根据username
        // 3.1 如果主键不为空的话
        if (StrUtil.isNotBlank(updatePO.getUserId())) {
            return sysUserMapper.updateById(updatePO) > 0;
        }
        // 3.2否则尝试按照username更新
        LambdaUpdateWrapper<SysUser> wrapper = Wrappers.lambdaUpdate(SysUser.class)
                .eq(SysUser::getUsername, updatePO.getUsername());
        return sysUserMapper.update(updatePO, wrapper) > 0;
    }


}

