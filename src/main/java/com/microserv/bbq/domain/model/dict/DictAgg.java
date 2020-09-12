package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.domain.factory.RepoFactory;
import com.microserv.bbq.domain.repository.DictRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * 领域模型--聚合
 * 根据某一类字典类型聚合来的集合,聚合根取字典类型
 *
 * @author jockeys
 * @since 2020/4/6
 */
@Setter
@Getter
@Accessors(chain = true)
public class DictAgg extends DictTypeVo {

    //----- field -----//
    private List<DictEntity> itemList;

    //----- constructor -----//
    public DictAgg(String type) {
        super(type);
    }

    //----- public method-----//
    public DictAgg fetch() {
        if (Objects.nonNull(this.getType())) {
            this.itemList = RepoFactory.get(DictRepo.class).selectByType(this.getType());
            return this;
        }
        return this;
    }
    public static DictAgg of(String type){
        return new DictAgg(type).fetch();
    }
}
