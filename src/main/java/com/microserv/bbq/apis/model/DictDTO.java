package com.microserv.bbq.apis.model;

import com.microserv.bbq.apis.assembler.IApiAssembler;
import com.microserv.bbq.domain.model.dict.DictEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 数据字典数据传输对象
 *
 * @author jockeys
 * @since 2020/2/4
 */
@Data
@AllArgsConstructor
public class DictDTO implements IApiAssembler<DictEntity> {

    private String id;

    @NotNull(message = "字典名称不能为空")
    private String name;

    @NotNull(message = "字典类型不能为空")
    private String type;

    @NotBlank(message = "字典码不能为空")
    private String code;

    @NotBlank(message = "字典值不能为空")
    private String value;

    private Integer orderNum;

    private String remark;

    /**
     * 从实体解析为传输对象
     */
    public DictDTO(@NotNull DictEntity entity) {
        ModelUtils.convert(entity, this);
    }

}
