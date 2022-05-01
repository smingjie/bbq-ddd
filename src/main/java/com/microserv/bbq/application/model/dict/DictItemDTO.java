package com.microserv.bbq.application.model.dict;

import com.microserv.bbq.domain.dict.model.entity.DictItemEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ~
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class DictItemDTO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "字典码")
    private String key;
    @ApiModelProperty(value = "字典值")
    private String value;

    public DictItemDTO(DictItemEntity e) {
        this.setId(e.getId()).setKey(e.getCode()).setValue(e.getValue());
    }
}