package com.microserv.bbq.infrastructure.general.security;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户鉴权信息-封装
 *
 * @author mingjie
 * @date 2022/3/19
 */
@Data
public class LoginUser implements Serializable {
    private String userId;     // 用户id
    private String requestId;  // 请求id
}
