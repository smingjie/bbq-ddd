package com.microserv.bbq.apis.model;

import com.google.common.collect.Lists;
import com.microserv.bbq.domain.model.dict.DictAgg;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 字典的视图模型
 *
 * @author jockeys
 * @since 2020/4/15
 */
@Data
public class DictTypeVo {
    private String type;
    private String name;
    private List<DictItem> items;

    @Data
    @Accessors(chain = true)
    public class DictItem {
        private String id;
        private String key;
        private String value;
    }

    /**
     * todo 解析为传输对象
     */
    public DictTypeVo(@NotNull DictAgg agg) {
        this.type = agg.getType();
        this.name = agg.getName();
        this.items = Lists.transform(agg.getItemList(), o -> new DictItem()
                .setId(o.getId()).setKey(o.getCode()).setValue(o.getValue())
        );

    }
}
