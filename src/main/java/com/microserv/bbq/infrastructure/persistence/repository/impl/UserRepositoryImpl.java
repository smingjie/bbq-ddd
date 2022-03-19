package com.microserv.bbq.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.user.UserEntity;
import com.microserv.bbq.domain.user.repository.UserRepository;
import com.microserv.bbq.infrastructure.persistence.po.SysUser;
import com.microserv.bbq.infrastructure.persistence.repository.impl.base.IBaseRepositoryImpl;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;
/**
 * User领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements IBaseRepositoryImpl<UserEntity, SysUser>, UserRepository {
    private final SysUserMapper userMapper;

    @Override
    public boolean delete(UserEntity item) {
        return false;
    }

    @Override
    public boolean insert(UserEntity item) {
	    return Objects.nonNull(item) && userMapper.insert(domain2po(item, SysUser.class)) > 0;
    }

    @Override
    public UserEntity selectById(String id) {
        return po2domain(userMapper.selectById(id), UserEntity.class);
    }

    @Override
    public UserEntity selectByUsername(String username) {
        return po2domain(ChainWrappers.lambdaQueryChain(userMapper).eq(SysUser::getUsername, username).one(),
                         UserEntity.class);
    }

    @Override
    public boolean update(UserEntity item) {
	    // 先转换
	    SysUser dbo = domain2po(item, SysUser.class);
	    if (Objects.isNull(dbo)) {
		    return false;
	    }
	    // 如果主键不为空的话
	    if (StrUtil.isNotBlank(dbo.getUserId())) {
		    return userMapper.updateById(dbo) > 0;
	    }
	    // 否则尝试按照username更新
	    LambdaUpdateWrapper<SysUser> wrapper = Wrappers.lambdaUpdate(SysUser.class)
			    .eq(SysUser::getUsername, dbo.getUsername()) ;
	    return userMapper.update(dbo, wrapper) > 0;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
