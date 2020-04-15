package com.microserv.bbq.apis.controller;

import com.microserv.bbq.apis.model.DictDto;
import com.microserv.bbq.domain.model.dict.DictAgg;
import com.microserv.bbq.domain.model.dict.DictEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * @author jockeys
 * @since 2020/4/12
 */
@Slf4j
@RestController
@RequestMapping("/dict")
public class DictController {

    @GetMapping
    public DictAgg getDictsByType(@RequestParam String type) {
        return new DictAgg(type).fetch();
    }

    @GetMapping("/one")
    public DictEntity getDictDetail(@RequestParam String type, @RequestParam String key) {
        return new DictEntity().setType(type).setCode(key).get();
    }

    @GetMapping("/{id}")
    public DictEntity getDictDetail(@PathVariable String id) {
        return new DictEntity(id).get();
    }

    @PostMapping
    public String saveDictDetail(@RequestBody DictDto param) {
        DictEntity entity = param.trans2Domain(param, DictEntity.class);
        return entity.saveOrUpdate().getId();
    }

    @PutMapping
    public DictEntity updateDictDetail(@RequestBody DictDto param) {
        DictEntity entity = param.trans2Domain(param, DictEntity.class);
        return entity.saveOrUpdate();
    }


}
