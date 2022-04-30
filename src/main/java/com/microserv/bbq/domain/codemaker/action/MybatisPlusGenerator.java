package com.microserv.bbq.domain.codemaker.action;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.microserv.bbq.domain.codemaker.CodeGenerator;

/**
 * mybatis plus code generator
 *
 * @author jockeys
 * @since 2020/4/4
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 加载配置
        CodeGenerator config = new CodeGenerator();
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(config.getGlobalConfig());
        mpg.setDataSource(config.getDataSourceConfig());
        mpg.setPackageInfo(config.getPackageConfig());
        mpg.setStrategy(config.getStrategyConfig());
        mpg.setTemplate(config.getTemplateConfig());
        mpg.setCfg(config.getInjectionConfig());
        mpg.setTemplateEngine(config.getTemplateEngine());
        mpg.execute();
    }

}
