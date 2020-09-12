package com.microserv.bbq.apis.controller;

import com.microserv.bbq.apis.model.DictDTO;
import com.microserv.bbq.apis.model.DictTypeVO;
import com.microserv.bbq.domain.model.dict.DictAgg;
import com.microserv.bbq.domain.model.dict.DictEntity;
import com.microserv.bbq.domain.model.dict.DictService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author jockeys
 * @since 2020/4/12
 */
@Slf4j
@RestController
public class DictController {

	@Resource
	private DictService dictService;

	@ApiOperation(value = "获取一个指定类型的字典集合")
	@GetMapping("/dict/agg")
	public DictTypeVO getDictByType(@RequestParam String type) {
		DictAgg agg = DictAgg.of(type);
		return new DictTypeVO(agg);
	}

	@ApiOperation(value = "获取一个字典记录详情，根据类型和键")
	@GetMapping("/dict/one")
	public DictEntity getDictDetail(@RequestParam String type, @RequestParam String key) {
		return new DictEntity().setType(type).setCode(key).get();
	}

	@ApiOperation(value = "获取一个字典记录详情，根据id")
	@GetMapping("/dict/{id}")
	public DictEntity getDictDetailById(@PathVariable String id) {
		return new DictEntity(id).get();
	}

	@ApiOperation(value = "新增一个字典记录")
	@PostMapping("/dict")
	public String saveDictDetail(@RequestBody DictDTO param) {
		DictEntity entity = param.trans2Domain(param, DictEntity.class);
		return entity.saveOrUpdate().getId();
	}

	@ApiOperation(value = "更新一个字典记录，根据id")
	@PutMapping("/dict/{id}")
	public DictEntity updateDictDetail(@PathVariable String id, @RequestBody DictDTO param) {
		param.setId(id);
		DictEntity entity = param.trans2Domain(param, DictEntity.class);
		return entity.saveOrUpdate();
	}

	@ApiOperation(value = "根据名称模糊查询字典记录")
	@GetMapping("/dict")
	public List<DictEntity> getDictListByValue(@RequestParam String value) {
		return dictService.getByValue(value);
	}

}
