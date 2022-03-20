package com.microserv.bbq.apis.apimodel.dict;

import com.microserv.bbq.domain.dict.DictEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import com.microserv.bbq.infrastructure.general.extension.assembler.IApiAssembler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class DictDTO implements IApiAssembler<DictEntity> {
	@ApiModelProperty(value = "id主键，更新的可选项")
	private String id;
	@ApiModelProperty(value = "字典名称", required = true)
	@NotNull(message = "字典名称不能为空")
	private String name;
	@ApiModelProperty(value = "字典类型", required = true)
	@NotNull(message = "字典类型不能为空")
	private String type;
	@ApiModelProperty(value = "字典码", required = true)
	@NotBlank(message = "字典码不能为空")
	private String code;
	@ApiModelProperty(value = "字典值", required = true)
	@NotBlank(message = "字典值不能为空")
	private String value;
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 从实体解析为传输对象
	 */
	public DictDTO(@NotNull DictEntity entity) {
		ModelUtils.convert(entity, this);
	}
}


