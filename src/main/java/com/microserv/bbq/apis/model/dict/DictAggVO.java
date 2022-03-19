package com.microserv.bbq.apis.model.dict;

import com.microserv.bbq.domain.model.dict.DictEntity;
import com.microserv.bbq.domain.model.dict.DictTypeAgg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
public class DictAggVO {
	@ApiModelProperty(value = "字典类型（聚合根）")
	private String type;
	@ApiModelProperty(value = "字典类型名称")
	private String name;
	@ApiModelProperty(value = "根据聚合根聚合的字典集合，记录包括id,key,value")
	private List<DictItem> items;

	/**
	 * todo 解析为传输对象
	 */
	public DictAggVO(  DictTypeAgg agg) {
		Objects.requireNonNull(agg);
		this.type = agg.getType();
		this.name = agg.getName();
		this.items = agg.getItemList().stream().map(DictItem::new).collect(Collectors.toList());
	}

	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	public static class DictItem {
		@ApiModelProperty(value = "id")
		private String id;
		@ApiModelProperty(value = "字典值")
		private String key;
		@ApiModelProperty(value = "字典值")
		private String value;

		public DictItem(DictEntity e) {
			this.setId(e.getId()).setKey(e.getCode()).setValue(e.getValue());
		}
	}
}


//~ Formatted by Jindent --- http://www.jindent.com
