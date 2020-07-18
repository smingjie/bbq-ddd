package com.microserv.bbq.domain.model.dict;

import com.microserv.bbq.infrastructure.general.toolkit.ApplicationUtils;
import com.microserv.bbq.infrastructure.persistence.DictDao;
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
public class DictAgg extends DictTypeVo {

    //----- field -----//
    private List<DictEntity> itemList;

    //----- constructor -----//
    public DictAgg(String type) {
        this.setType(type);
    }

    //----- public method-----//
    public DictAgg fetch() {
        if (Objects.nonNull(this.getType())) {
            this.itemList = ApplicationUtils.getBean(DictDao.class).selectByType(this.getType());
            return this;
        }
        return this;
    }
}
