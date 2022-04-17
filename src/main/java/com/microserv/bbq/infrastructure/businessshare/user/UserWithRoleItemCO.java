package com.microserv.bbq.infrastructure.businessshare.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户集合条目（Client Object）
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@Accessors
@ApiModel
public class UserWithRoleItemCO {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户姓名")
    private String userName;

    @ApiModelProperty("状态")
    private boolean enabled;

    @ApiModelProperty("用户创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("用户拥有的角色")
    private String roles;

}
