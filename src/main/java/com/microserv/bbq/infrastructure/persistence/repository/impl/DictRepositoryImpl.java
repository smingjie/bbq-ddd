package com.microserv.bbq.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import com.microserv.bbq.domain.dict.entity.DictEntity;
import com.microserv.bbq.domain.dict.entity.DictTypeEntity;
import com.microserv.bbq.domain.dict.repository.DictRepository;
import com.microserv.bbq.infrastructure.general.commonshare.exception.PersistException;
import com.microserv.bbq.infrastructure.general.commonshare.security.SecurityContext;
import com.microserv.bbq.infrastructure.persistence.assembler.SysDictAssembler;
import com.microserv.bbq.infrastructure.persistence.po.SysDict;
import com.microserv.bbq.infrastructure.persistence.repository.impl.mapper.SysDictMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Dict领域的仓储实现
 *
 * @author mingjie
 * @since 2022/03/19
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DictRepositoryImpl implements DictRepository {
    private final SysDictMapper sysDictMapper;
    private final SysDictAssembler sysDictAssembler;

    //-- 仓储实现
    @Override
    public DictEntity selectById(String id) {
        SysDict po = sysDictMapper.selectById(id);
        return sysDictAssembler.po2domain(po);
    }

    @Override
    public DictEntity selectByTypeAndCode(String type, String code) {
        SysDict po = ChainWrappers.lambdaQueryChain(sysDictMapper)
                .eq(SysDict::getType, type)
                .eq(SysDict::getCode, code)
                .one();
        return sysDictAssembler.po2domain(po);
    }

    @Override
    public List<DictEntity> searchByValue(String valueLike) {
        List<SysDict> poList = ChainWrappers.lambdaQueryChain(sysDictMapper)
                .like(SysDict::getValue, valueLike)
                .list();
        return poList.stream().map(sysDictAssembler::po2domain).collect(Collectors.toList());
    }

    @Override
    public List<DictTypeEntity> searchByTypeName(String typeNameLike) {
        List<SysDict> poList = ChainWrappers.lambdaQueryChain(sysDictMapper)
                .select(SysDict::getType, SysDict::getName)
                .like(SysDict::getName, typeNameLike)
                .list();
        if (CollectionUtils.isEmpty(poList)) {
            return Collections.emptyList();
        }
        return poList.stream()
                .map(sysDictAssembler::po2domainDictTypeEntity)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<DictEntity> selectByType(String type) {
        List<SysDict> poList = ChainWrappers.lambdaQueryChain(sysDictMapper)
                .eq(SysDict::getType, type)
                .list();
        return poList.stream().map(sysDictAssembler::po2domain).collect(Collectors.toList());
    }

    @Override
    public boolean insert(DictEntity item) {
        if (Objects.isNull(item)) {
            throw new PersistException("保存插入实体时，字典实体不能为空");
        }

        SysDict sysDict = sysDictAssembler.domain2po(item);
        return sysDictMapper.insert(sysDict) > 0;

    }

    @Override
    public boolean update(DictEntity item) {
        // 1 更新前校验
        if (Objects.isNull(item)) {
            throw new PersistException("更新实体时，字典实体不能为空");
        }
        if (StrUtil.isBlank(item.getId()) && !StrUtil.isAllNotBlank(item.getType(), item.getCode())) {
            throw new PersistException("更新实体时，若字典id为空,则字典类型和字典码组合都不能为空");
        }

        // 2 模型转换
        SysDict updatePO = sysDictAssembler.domain2po(item);
        updatePO.setCreateBy(SecurityContext.tryGetLoginUserId());
        updatePO.setUpdateTime(LocalDateTime.now());


        // 3 更新，优先根据字典id，若是没有则根据type+code组合
        // 3.1 如果主键不为空的话
        if (StrUtil.isNotBlank(updatePO.getId())) {
            return sysDictMapper.updateById(updatePO) > 0;
        }
        // 3.2否则尝试按照type+code组合
        LambdaUpdateWrapper<SysDict> wrapper = Wrappers.lambdaUpdate(SysDict.class)
                .eq(SysDict::getType, updatePO.getType())
                .eq(SysDict::getCode, updatePO.getCode());
        return sysDictMapper.update(updatePO, wrapper) > 0;
    }

    @Override
    public boolean delete(DictEntity item) {
        // 先转换
        SysDict dbo = sysDictAssembler.domain2po(item);

        if (Objects.isNull(dbo)) {
            return false;
        }
        // 如果主键不为空的话
        if (StrUtil.isNotBlank(dbo.getId())) {
            return sysDictMapper.deleteById(dbo.getId()) > 0;
        }
        //否则按照type code 删除
        LambdaQueryWrapper<SysDict> wrapper = Wrappers.lambdaQuery(SysDict.class)
                .eq(SysDict::getType, dbo.getType())
                .eq(SysDict::getCode, dbo.getCode());
        return sysDictMapper.delete(wrapper) > 0;
    }
}
