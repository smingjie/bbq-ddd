package com.microserv.bbq.apis.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 测试控制器
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @ApiOperation(value = "健康检查")
    @GetMapping("/health")
    public String getHealthStatus() {
        return "the server is running health";
    }

    @ApiOperation(value = "LocalDateTIme时间是否正确格式化")
    @GetMapping("/time")
    public JSONObject getTime() {
        return new JSONObject().fluentPut("now", LocalDateTime.now());
    }

}
