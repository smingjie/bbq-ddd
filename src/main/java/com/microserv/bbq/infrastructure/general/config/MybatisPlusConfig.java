package com.microserv.bbq.infrastructure.general.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author jockeys
 * @since 2020/4/12
 */

@Configuration
@MapperScan({"com.microserv.bbq.infrastructure.persistence.mapper"})
public class MybatisPlusConfig {
}
