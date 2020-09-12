package com.microserv.bbq.domain.model.dict;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 领域模型-值对象
 * Value Object => which is called Vo in our bbq-framework
 *
 * @author jockeys
 * @since 2020/4/12
 */
@Data
@Accessors(chain = true)
public class DictTypeVo {
	/***类型 **/
	private String type;
	/***类型名称 **/
	private String name;

	public DictTypeVo(String type) {
		this.type = type;
	}
}
