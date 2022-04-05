package com.microserv.bbq.infrastructure.share.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 键值对的客户端对象
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Accessors(chain = true)
public class DictionaryCO implements Serializable {
    private String key;
    private String value;
}
