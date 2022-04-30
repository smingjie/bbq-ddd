package com.microserv.bbq.apis.controller;

import com.microserv.bbq.application.model.dict.dto.DictAggVO;
import com.microserv.bbq.application.model.dict.dto.DictCreateParam;
import com.microserv.bbq.application.model.dict.dto.DictUpdateParam;
import com.microserv.bbq.application.model.dict.assembler.DictApiAssembler;
import com.microserv.bbq.domain.dict.model.entity.DictEntity;
import com.microserv.bbq.domain.dict.model.entity.DictTypeEntity;
import com.microserv.bbq.domain.dict.action.DictDomainService;
import com.microserv.bbq.domain.dict.model.DictTypeAgg;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 字典域 控制器
 *
 * @author jockeys
 * @since 2020/4/12
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DictController {
    private final DictDomainService dictDomainService;
    private final DictApiAssembler dictApiAssembler;

    @ApiOperation(value = "获取一个指定类型的字典集合")
    @GetMapping("/dict/agg")
    public DictAggVO getDictByType(@RequestParam String type) {
        DictTypeAgg agg = DictTypeAgg.getInstance(type);
        return new DictAggVO(agg);
    }

    @ApiOperation(value = "获取所有字典类型集合")
    @GetMapping("/dict/types")
    public List<DictTypeEntity> getDictTypes(@RequestParam String name) {
        return dictDomainService.getTypes(name);
    }

    @ApiOperation(value = "获取一个字典记录详情，根据类型和键")
    @GetMapping("/dict/one")
    public DictEntity getDictDetailByTypeAndCode(@RequestParam String type, @RequestParam String code) {
        return DictEntity.getInstance(type, code);
    }

    @ApiOperation(value = "获取一个字典记录详情，根据id")
    @GetMapping("/dict/{id}")
    public DictEntity getDictDetailById(@PathVariable String id) {
        return DictEntity.getInstance(id);
    }

    @ApiOperation(value = "新增一个字典记录")
    @PostMapping("/dict")
    public String saveDictDetail(@Validated @RequestBody DictCreateParam param) {
        DictEntity entity = dictApiAssembler.trans2Domain(param, DictEntity.class);
        return entity.saveOrUpdate().getId();
    }

    @ApiOperation(value = "更新一个字典记录，根据id")
    @PutMapping("/dict/{id}")
    public DictEntity updateDictDetail(@PathVariable String id, @RequestBody DictUpdateParam param) {
        DictEntity entity = dictApiAssembler.trans2Domain(param.setId(id), DictEntity.class);
        return entity.saveOrUpdate();
    }

    @ApiOperation(value = "根据名称模糊查询字典记录")
    @GetMapping("/dict")
    public List<DictEntity> getDictListByValue(@RequestParam String value) {
        return dictDomainService.getByValue(value);
    }

}
