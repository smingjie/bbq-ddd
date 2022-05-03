package com.microserv.bbq.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.microserv.bbq.application.repository.UserApplicationRepository;
import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.domain.user.model.part.UserContactVObj;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.domain.user.model.part.UserDictVObj;
import com.microserv.bbq.infrastructure.general.common.exception.PersistException;
import com.microserv.bbq.infrastructure.general.extension.ddd.annotation.DomainRepository;
import com.microserv.bbq.infrastructure.general.common.security.SecurityContext;
import com.microserv.bbq.infrastructure.persistence.converter.SysUserConverter;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysUserMapper;
import com.microserv.bbq.infrastructure.general.common.page.PageResult;
import com.microserv.bbq.infrastructure.general.module.user.UserSearchParam;
import com.microserv.bbq.infrastructure.general.module.user.UserWithRoleItemCO;
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
public class UserRepositoryImpl implements UserRepository, UserApplicationRepository {
    private final SysUserMapper sysUserMapper;         // 用户Mapper
    private final SysUserConverter sysUserAssembler;   // 用户Assembler

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

        SysUser sysUser = sysUserAssembler.domain2po(item);
        return sysUserMapper.insert(sysUser) > 0;
    }

    @Override
    public UserEntity selectById(String id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        return sysUserAssembler.po2domain(sysUser);
    }

    @Override
    public UserEntity selectByUsername(String username) {
        SysUser sysUser = ChainWrappers.lambdaQueryChain(sysUserMapper)
                .eq(SysUser::getUsername, username)
                .one();
        return sysUserAssembler.po2domain(sysUser);

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
    public List<UserContactVObj> selectContactByUserIds(List<String> userIds) {
        List<SysUser> userList = ChainWrappers.lambdaQueryChain(sysUserMapper)
                .select(SysUser::getUserId, SysUser::getMobile)
                .in(SysUser::getUserId,userIds)
                .list();
        return CollectionUtils.isEmpty(userList)
                ? Collections.emptyList() :
                userList.stream().map(sysUserAssembler::po2domainUserContactVObj).collect(Collectors.toList());
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
        SysUser updatePO = sysUserAssembler.domain2po(item);
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


    @Override
    public PageResult<UserWithRoleItemCO> listUserByPage(UserSearchParam param) {
        PageInfo<UserWithRoleItemCO> page = PageMethod
                .startPage(param.getCurrent(), param.getPageSize())
                .doSelectPageInfo(() -> sysUserMapper.selectListBySearchParam(param));

        return PageResult.valueOf(page);
    }
}

