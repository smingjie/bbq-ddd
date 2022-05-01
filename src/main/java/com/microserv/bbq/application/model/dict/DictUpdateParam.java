package com.microserv.bbq.application.model.dict;

import com.microserv.bbq.domain.dict.model.entity.DictEntity;
import com.microserv.bbq.infrastructure.general.toolkit.ModelUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 数据字典更新参数
 *
 * @author jockeys
 * @since 2021/3/21
 */
@Data
@AllArgsConstructor
@ApiModel
@Accessors(chain = true)
public class DictUpdateParam implements Serializable {
	@ApiModelProperty(value = "id主键",hidden = true)
	private String id;
	@ApiModelProperty(value = "字典名称") 
	private String name;
	@ApiModelProperty(value = "字典类型") 
	private String type;
	@ApiModelProperty(value = "字典码")
	private String code;
	@ApiModelProperty(value = "字典值")
	private String value;
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 从实体解析为传输对象
	 */
	public DictUpdateParam(@NotNull DictEntity entity) {
		ModelUtils.convert(entity, this);
	}
}


