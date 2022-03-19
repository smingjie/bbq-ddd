package com.microserv.bbq.infrastructure.general.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MP 全局配置
 * @author jockeys
 * @since 2020/4/12
 */

@Configuration
@MapperScan({"com.microserv.bbq.infrastructure.persistence.repo.mapper"})
public class MybatisPlusConfig {
}
