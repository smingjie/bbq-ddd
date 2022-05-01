package com.microserv.bbq.application.model.dict;

import com.microserv.bbq.domain.dict.model.DictTypeAgg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 字典的视图模型
 *
 * @author jockeys
 * @since 2020/4/15
 */
@Data
@NoArgsConstructor
@ApiModel(description = "字典类型为聚合根的聚合视图对象")
public class DictGroupDTO implements Serializable {
    @ApiModelProperty(value = "字典类型（聚合根）")
    private String type;
    @ApiModelProperty(value = "字典类型名称")
    private String name;
    @ApiModelProperty(value = "根据聚合根聚合的字典集合，记录包括id,key,value")
    private List<DictItemDTO> items;

    public DictGroupDTO(DictTypeAgg agg) {
        Objects.requireNonNull(agg);
        this.type = agg.getType();
        this.name = agg.getName();
        this.items = agg.getItemList().stream().map(DictItemDTO::new).collect(Collectors.toList());
    }

}
