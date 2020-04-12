package com.microserv.bbq.infrastructure.persistence;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.microserv.bbq.domain.model.dict.DictEntity;
import com.microserv.bbq.domain.repository.DictRepo;
import com.microserv.bbq.infrastructure.persistence.base.IDaoAssembler;
import com.microserv.bbq.infrastructure.persistence.mapper.SysDictMapper;
import com.microserv.bbq.infrastructure.persistence.po.SysDict;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * Data Access Object
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Slf4j
@Repository
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class DictDao implements DictRepo, IDaoAssembler<SysDict, DictEntity> {
    private final SysDictMapper dictMapper;

    //-- 仓储实现
    @Override
    public DictEntity select(String id) {
        SysDict dbo = dictMapper.selectById(id);
        return transferDomain(dbo, DictEntity.class);
    }

    @Override
    public DictEntity selectOne(String type, String key) {
        SysDict dbo = new LambdaQueryChainWrapper<>(dictMapper)
                .eq(SysDict::getType, type)
                .eq(SysDict::getCode, key)
                .one();
        return transferDomain(dbo, DictEntity.class);
    }

    @Override
    public List<DictEntity> selectByType(String type) {
        List<SysDict> dbos = new LambdaQueryChainWrapper<>(dictMapper)
                .eq(SysDict::getType, type)
                .list();
        return transferDomain(dbos, DictEntity.class);
    }

    @Override
    public boolean insert(DictEntity item) {

        return Objects.nonNull(item) && dictMapper.insert(transferDbo(item, SysDict.class)) > 0;
    }

    @Override
    public boolean update(DictEntity item) {
        // 先转换
        SysDict dbo = transferDbo(item, SysDict.class);
        if (Objects.isNull(dbo)) {
            return false;
        }
        // 如果主键不为空的话
        if (StrUtil.isNotBlank(dbo.getId())) {
            return dictMapper.updateById(dbo) > 0;
        }
        // 否则尝试按照type code 更新
        LambdaUpdateWrapper wrapper = new LambdaUpdateWrapper<SysDict>()
                .eq(SysDict::getType, dbo.getType())
                .eq(SysDict::getCode, dbo.getCode());
        return dictMapper.update(dbo, wrapper) > 0;
    }

    @Override
    public boolean delete(DictEntity item) {
        // 先转换
        SysDict dbo = transferDbo(item, SysDict.class);

        if (Objects.isNull(dbo)) {
            return false;
        }
        // 如果主键不为空的话
        if (StrUtil.isNotBlank(dbo.getId())) {
            return dictMapper.deleteById(dbo.getId()) > 0;
        }
        //否则按照type code 删除
        LambdaQueryWrapper wrapper = new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getType, dbo.getType())
                .eq(SysDict::getCode, dbo.getCode());
        return dictMapper.delete(wrapper) > 0;
    }
}
