package com.microserv.bbq.apis.apimodel.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.tomcat.util.security.MD5Encoder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * API层模型-用户登录参数封装
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
public class UserLoginParam implements Serializable {
    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "用户名密码不能为空")
    private String password;

    public String getEncodePassword() {
        // 假设有一种加密算法在此
        return MD5Encoder.encode(this.password.getBytes());
    }

}
