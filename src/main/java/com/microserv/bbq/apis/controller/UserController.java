package com.microserv.bbq.apis.controller;


import com.microserv.bbq.domain.user.UserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author mpGenerator
 * @since 2020-04-25
 */
@RestController
public class UserController {
	@ApiOperation(value = "获取用户的基本信息")
	@GetMapping("/user/{userId}/info")
	public UserEntity getUserInfoByUserId(@PathVariable String userId) {
		return new UserEntity();
	}
}

