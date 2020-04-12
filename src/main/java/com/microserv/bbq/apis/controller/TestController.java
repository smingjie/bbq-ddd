package com.microserv.bbq.apis.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Slf4j
@RestController
@RequestMapping("/health")
public class TestController {
    @ApiOperation(value = "健康检查")
    @GetMapping
    public String getHealthStatus() {
        return "ok";
    }
}
