package com.microserv.bbq.generator;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.microserv.bbq.infrastructure.persistence.common.AbstractBasePo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus（mp）配置属性
 *
 * @author jockeys
 * @since 2020/4/4
 */
@Data
public class GeneratorConfig {
    /**
     * 全局策略配置
     */
    private GlobalConfig globalConfig;
    /**
     * 数据源配置，通过该配置，指定需要生成代码的具体数据库
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * 包名配置，通过该配置，指定生成代码的包路径
     */
    private PackageConfig packageConfig;
    /**
     * 模板配置，可自定义代码生成的模板，实现个性化操作
     */
    private TemplateConfig templateConfig;
    /**
     * 模板引擎选择
     */
    private AbstractTemplateEngine templateEngine;
    /**
     * 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
     */
    private StrategyConfig strategyConfig;
    /**
     * 注入配置，通过该配置，可注入自定义参数等操作以实现个性化操作
     */
    private InjectionConfig injectionConfig;
    /**
     * 项目根目录
     */
    private String projectPath = System.getProperty("user.dir");
    private String[] commonColumns = "create_by,create_time,update_by,update_time".split(",");


    public GeneratorConfig() {
        initConfig();  //初始化预设配置
        //模板引擎配置
        this.templateEngine = new VelocityTemplateEngine();
        // 全局配置
        this.globalConfig.setOutputDir(projectPath + "/src/main/java");
        this.globalConfig.setAuthor("mpGenerator");
        this.globalConfig.setFileOverride(true);
        this.globalConfig.setOpen(false);
        this.globalConfig.setSwagger2(true);
        // 数据源配置
        this.dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/bbq?serverTimezone=Asia/Shanghai&useUnicode=true&useSSL=false");
        this.dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        this.dataSourceConfig.setUsername("root");
        this.dataSourceConfig.setPassword("123456");
        // 包配置
        this.packageConfig.setModuleName("code");
        this.packageConfig.setParent("com.microserv.bbq.generator");
        // 策略配置
        this.strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        this.strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        this.strategyConfig.setEntityLombokModel(true);
        this.strategyConfig.setRestControllerStyle(true);
        this.strategyConfig.setEntityTableFieldAnnotationEnable(true);
        this.strategyConfig.setExclude("tb_cfg");
       // this.strategyConfig.setControllerMappingHyphenStyle(true);
        this.strategyConfig.setSuperEntityClass(AbstractBasePo.class);// 公共父类
        this.strategyConfig.setSuperEntityColumns(commonColumns); // 写于父类中的公共字段
        //this.strategyConfig.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");  // 公共父类
        //this.strategyConfig.setInclude("表名，多个英文逗号分割".split(","));
        //this.strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");

        // 配置模板  --指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        //this.templateConfig.setEntity("templates/entity2.java");
        //this.templateConfig.setService();
        //this.templateConfig.setController();
        this.templateConfig.setXml(null);
        // 如果模板引擎是 velocity
        String templatePath = "/templates/entity.java.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
//        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        injectionConfig.setFileOutConfigList(focList);
    }

    private void initConfig() {
        this.globalConfig = new GlobalConfig();
        this.dataSourceConfig = new DataSourceConfig();
        this.packageConfig = new PackageConfig();
        this.strategyConfig = new StrategyConfig();
        this.templateConfig = new TemplateConfig();
        this.injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
    }
}
