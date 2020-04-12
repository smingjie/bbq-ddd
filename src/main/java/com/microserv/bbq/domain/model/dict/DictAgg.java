package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.domain.repository.RepoContext;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * 聚合
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Data
@Accessors(chain = true)
public class DictAgg {

    //----- field -----//
    private DictType dictType;
    private List<DictEntity> itemList;

    //----- constructor -----//
    public DictAgg(String type) {
        this.dictType = new DictType(type);
    }

    //----- public method-----//
    public DictAgg fetch() {
        if (Objects.nonNull(this.dictType)) {
            this.itemList = RepoContext.dictRepo.selectByType(this.dictType.getType());
            return this;
        }
        return this;
    }
}
