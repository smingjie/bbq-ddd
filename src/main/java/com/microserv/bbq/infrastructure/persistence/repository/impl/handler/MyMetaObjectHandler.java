package com.microserv.bbq.infrastructure.persistence.repository.impl.handler;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.microserv.bbq.infrastructure.general.commonshare.security.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author mingjie
 * @date 2022年3月19日22:03:43
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATE_BY = "createBy";
    private static final String UPDATE_BY = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("start insert fill ....");
        this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, CREATE_BY, String.class, SecurityContext.tryGetLoginUserId());
        this.strictInsertFill(metaObject, UPDATE_BY, String.class, SecurityContext.tryGetLoginUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");  // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, UPDATE_BY, String.class, SecurityContext.tryGetLoginUserId());

    }
}