package com.microserv.bbq.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.infrastructure.persistence.po.SysDict;
import com.microserv.bbq.infrastructure.persistence.repository.impl.base.IBaseRepositoryImpl;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysDictMapper;
import com.microserv.bbq.domain.model.dict.DictEntity;
import com.microserv.bbq.domain.repository.DictRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Dict领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DictRepositoryImpl implements IBaseRepositoryImpl<DictEntity, SysDict>, DictRepo {
	private final SysDictMapper dictMapper;

	//-- 仓储实现
	@Override
	public DictEntity select(String id) {
		SysDict po = dictMapper.selectById(id);
		return po2domain(po, DictEntity.class);
	}

	@Override
	public DictEntity selectOne(String type, String key) {
		SysDict dbo = ChainWrappers.lambdaQueryChain(dictMapper)
				.eq(SysDict::getType, type)
				.eq(SysDict::getCode, key)
				.one();
		return po2domain(dbo, DictEntity.class);
	}

	@Override
	public List<DictEntity> selectByValue(String valueLike) {
		List<SysDict> poList = ChainWrappers.lambdaQueryChain(dictMapper)
				.like(SysDict::getValue, valueLike)
				.list();
		return po2domain(poList, DictEntity.class);
	}

	@Override
	public List<DictEntity> selectByType(String type) {
		List<SysDict> poList = ChainWrappers.lambdaQueryChain(dictMapper)
				.eq(SysDict::getType, type)
				.list();
		return po2domain(poList, DictEntity.class);
	}

	@Override
	public boolean insert(DictEntity item) {
		return Objects.nonNull(item) && dictMapper.insert(domain2po(item, SysDict.class)) > 0;
	}

	@Override
	public boolean update(DictEntity item) {
		// 先转换
		SysDict dbo = domain2po(item, SysDict.class);
		if (Objects.isNull(dbo)) {
			return false;
		}
		// 如果主键不为空的话
		if (StrUtil.isNotBlank(dbo.getId())) {
			return dictMapper.updateById(dbo) > 0;
		}
		// 否则尝试按照type code 更新
		LambdaUpdateWrapper<SysDict> wrapper = Wrappers.lambdaUpdate(SysDict.class)
				.eq(SysDict::getType, dbo.getType())
				.eq(SysDict::getCode, dbo.getCode());
		return dictMapper.update(dbo, wrapper) > 0;
	}

	@Override
	public boolean delete(DictEntity item) {
		// 先转换
		SysDict dbo = domain2po(item, SysDict.class);

		if (Objects.isNull(dbo)) {
			return false;
		}
		// 如果主键不为空的话
		if (StrUtil.isNotBlank(dbo.getId())) {
			return dictMapper.deleteById(dbo.getId()) > 0;
		}
		//否则按照type code 删除
		LambdaQueryWrapper<SysDict> wrapper = Wrappers.lambdaQuery(SysDict.class)
				.eq(SysDict::getType, dbo.getType())
				.eq(SysDict::getCode, dbo.getCode());
		return dictMapper.delete(wrapper) > 0;
	}
}
