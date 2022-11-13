package com.microserv.bbq.apis.controller;


import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microserv.bbq.domain.user.model.UserEntity;
import com.microserv.bbq.infrastructure.general.common.page.PageResult;
import com.microserv.bbq.infrastructure.general.module.user.UserWithRoleItemCO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认控制器
 *
 * @author jockeys
 * @since 2020/4/11
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class IndexController {
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

    @GetMapping(value = "test/sync")
    public PageResult<UserWithRoleItemCO> testSync() throws Exception {
        long start = System.currentTimeMillis();
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity("1").setMobile("12345678901").setName("test1"));
        users.add(new UserEntity("2").setMobile("12345678902").setName("test2"));
        users.add(new UserEntity("3").setMobile("12345678903").setName("test3"));
        users.add(new UserEntity("4").setMobile("12345678904").setName("test4"));
        users.add(new UserEntity("5").setMobile("12345678905").setName("test5"));
        users.add(new UserEntity("6").setMobile("12345678906").setName("test6"));
        users.add(new UserEntity("7").setMobile("12345678907").setName("test7"));
        users.add(new UserEntity("8").setMobile("12345678908").setName("test8"));
        users.add(new UserEntity("9").setMobile("12345678909").setName("test9"));
        users.add(new UserEntity("10").setMobile("12345678910").setName("test10"));
        IPage<UserEntity> pageList = new Page<>();
        pageList.setPages(1);
        pageList.setCurrent(1);
        pageList.setRecords(users);

        PageResult<UserWithRoleItemCO> result = PageResult.transform(pageList, this::convert);
        log.info("同步执行，所有转换累计耗时 {} ms，个数 {} ", System.currentTimeMillis() - start, users.size());
        return result;
    }

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping(value = "test/async")
    public PageResult<UserWithRoleItemCO> testAsync(@RequestParam(required = false, defaultValue = "10") Integer size) throws Exception {
        long start = System.currentTimeMillis();
        List<UserEntity> users = new ArrayList<>();
        int total = size == null ? 10 : size;
        for (int i = 0; i < total; i++) {
            String intStr = String.valueOf(i);
            users.add(new UserEntity(intStr).setMobile("phone" + intStr).setName("myname" + intStr));
        }
        IPage<UserEntity> pageList = new Page<>();
        pageList.setPages(1);
        pageList.setCurrent(1);
        pageList.setRecords(users);

        PageResult<UserWithRoleItemCO> result = PageResult.transformAsync(
                pageList,
                this::convert,
                null
        );
        log.info("异步执行-所有转换累计耗时 {} ms，个数 {} ", System.currentTimeMillis() - start, users.size());
        return result;
    }

    private UserWithRoleItemCO convert(UserEntity o) {
        long start = System.currentTimeMillis();

        ThreadUtil.safeSleep(500);
        UserWithRoleItemCO co = new UserWithRoleItemCO();
        co.setUserId(o.getUserId());
        co.setRoles(o.getUserId());
        co.setUserName(o.getMobile());
        co.setEnabled(true);

        log.info("一次转换耗时 {} ms", System.currentTimeMillis() - start);
        return co;
    }


}
