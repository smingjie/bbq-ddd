package com.microserv.bbq.apis.model;

import com.google.common.collect.Lists;
import com.microserv.bbq.domain.model.dict.DictAgg;
import com.microserv.bbq.domain.model.dict.DictEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 字典的视图模型
 *
 * @author jockeys
 * @since 2020/4/15
 */
@Data
@NoArgsConstructor
public class DictTypeVO {
	private String type;
	private String name;
	private List<DictItem> items;

	@Data
	@Accessors(chain = true)
	@NoArgsConstructor
	public static class DictItem {
		private String id;
		private String key;
		private String value;

		public DictItem(DictEntity e) {
			this.setId(e.getId()).setKey(e.getCode()).setValue(e.getValue());
		}
	}

	/**
	 * todo 解析为传输对象
	 */
	public DictTypeVO(@NotNull DictAgg agg) {
		this.type = agg.getType();
		this.name = agg.getName();
		this.items = Lists.transform(agg.getItemList(), DictItem::new);
	}
}
