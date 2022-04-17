package com.microserv.bbq.apis.controller;


import com.microserv.bbq.apis.apimodel.user.UserCreateParam;
import com.microserv.bbq.apis.apimodel.user.UserLoginParam;
import com.microserv.bbq.apis.assembler.UserApiAssembler;
import com.microserv.bbq.application.service.UserApplicationService;
import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.service.UserDomainService;
import com.microserv.bbq.infrastructure.general.commonshare.page.PageResult;
import com.microserv.bbq.infrastructure.businessshare.user.UserWithRoleItemCO;
import com.microserv.bbq.infrastructure.businessshare.user.UserSearchParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户控制器
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDomainService userDomainService;
    private final UserApiAssembler userApiAssembler;
    private final UserApplicationService userAppService;

    @ApiOperation(value = "获取用户的基本信息")
    @GetMapping("/users/{userId}/info")
    public UserEntity getUserInfoByUserId(@PathVariable String userId) {
        log.info("执行用户信息查询,userId={}", userId);
        return UserEntity.getInstanceByUserId(userId);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/users/login")
    public UserEntity login(@Validated @RequestBody UserLoginParam param) {
        return userDomainService.getUserEntityWhenUsernamePasswordMatched(param.getUsername(), param.getEncodePassword());
    }

    @ApiOperation(value = "用户创建")
    @PostMapping("/users/one")
    public UserEntity login(@Validated @RequestBody UserCreateParam param) {
        UserEntity entity = userApiAssembler.trans2Domain(param, UserEntity.class);  // 模型转换
        return entity.saveOrUpdate();        // 执行保存,并返回数据库查到的最新的结果
    }

    @ApiOperation(value = "筛选用户集合（带角色信息）")
    @GetMapping("/users/list")
    public PageResult<UserWithRoleItemCO> listUserByPage(@Validated @ModelAttribute UserSearchParam searchParam) {
        log.info("执行筛选用户集合（带角色信息）,searchParam={}", searchParam);
        return userAppService.listUserByPage(searchParam);
    }

}

