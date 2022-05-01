package com.microserv.bbq.application.model.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * API层模型-用户创建参数封装
 *
 * @author mingjie
 * @date 2022/3/20
 */
@Data
@Accessors(chain = true)
public class UserCreateParam {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;
    @ApiModelProperty(value = "启用禁用，默认是true")
    private Boolean enabled = true;
}
