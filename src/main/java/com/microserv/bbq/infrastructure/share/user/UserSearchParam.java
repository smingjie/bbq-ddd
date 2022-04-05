package com.microserv.bbq.infrastructure.share.user;

import com.microserv.bbq.infrastructure.share.page.PageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户查询参数封装
 *
 * @author mingjie
 * @date 2022/4/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserSearchParam extends PageQueryParam {

    @ApiModelProperty("角色名称模糊值")
    private String roleName;

    @ApiModelProperty("用户名称模糊值")
    private String userName;

}
