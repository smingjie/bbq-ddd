package com.microserv.bbq.apis.controller;


import com.microserv.bbq.apis.apimodel.user.UserCreateParam;
import com.microserv.bbq.apis.apimodel.user.UserLoginParam;
import com.microserv.bbq.apis.assembler.UserApiAssembler;
import com.microserv.bbq.domain.user.entity.UserEntity;
import com.microserv.bbq.domain.user.service.UserDomainService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserDomainService userDomainService;
    @Autowired
    private UserApiAssembler userApiAssembler;

    @ApiOperation(value = "获取用户的基本信息")
    @GetMapping("/users/{userId}/info")
    public UserEntity getUserInfoByUserId(@PathVariable String userId) {
        log.info("执行用户信息查询,userId={}", userId);
        return new UserEntity(userId).get();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/users/login")
    public UserEntity login(@Validated @RequestBody UserLoginParam param) {
        return userDomainService.getUserEntityWhenUsernamePasswordMatched(param.getUsername(), param.getEncodePassword());
    }

    @ApiOperation(value = "用户创建")
    @PostMapping("/users/one")
    public UserEntity login(@Validated @RequestBody UserCreateParam param) {
        // 模型转换
        UserEntity entity = userApiAssembler.trans2Domain(param, UserEntity.class);

        // 执行保存
        entity.saveOrUpdate();

        // 返回数据库查到的最新的结果
        return entity;
    }
}

