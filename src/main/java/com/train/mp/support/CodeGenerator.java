package com.train.mp.support;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis plus  代码生成器
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/2 0002 10:42
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    //数据库连接地址
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mp?serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    //用户名
    private static final String USER_NAME = "root";
    //密码
    private static final String PASSWORD = "admin";
    //普通驱动类
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    //CJ驱动类
    private static final String DRIVER_CLASS_CJ = "com.mysql.cj.jdbc.Driver";
    //mysql驱动 p6spy
    public static final String DRIVER_CLASS_P6 = "com.p6spy.engine.spy.P6SpyDriver";
    //项目基础包
    private static final String BASE_PACKAGE = "com.train.mp";
    //作者
    private static final String AUTHOR = "Jim clark";
    //freeMarker 模板路径
    private static final String FREEMARKER_PATH = "/templates/mapper.xml.ftl";

    //velocity 模板路径
    private static final String VELOCITY_PATH = "/templates/mapper.xml.vm";
    //表前缀
    private static final String TABLE_PREFIX = "mp_";


    public static void main(String[] args) {
        //String tableName = "mp_comment";//表名 格式 aa_bbb
        String[] tableNames = {"mp_user", "mp_article", "mp_comment", "mp_replay", "mp_module"};
        AutoGenerator mpg = new AutoGenerator(); // 代码生成器类

        // 设置全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取项目的绝对物理地址
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setActiveRecord(true);//开启AR模式 默认false
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_CLASS);//数据库驱动
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));//maven多模块项目使用
        pc.setParent(BASE_PACKAGE);
        pc.setMapper("dao");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(FREEMARKER_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;//+ pc.getModuleName() + "/"
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//lombok
        strategy.setRestControllerStyle(true);

//      strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setSuperControllerClass("com.train.mp.controller.BaseController");// 公共父类
//      strategy.setSuperEntityColumns("id");// 写于父类中的公共字段
//      strategy.setTablePrefix(pc.getModuleName() + "_");

        strategy.setTablePrefix(TABLE_PREFIX);//生成的实体中不包含表的前缀
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);

        strategy.setLogicDeleteFieldName("enable");

//字段填充
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("creator_id", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("modifier_id", FieldFill.UPDATE));
        tableFillList.add(new TableFill("modify_time", FieldFill.UPDATE));
        strategy.setTableFillList(tableFillList);

        mpg.setStrategy(strategy);//设置策略
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());// 设置模板引擎
        mpg.execute();//执行
    }

}
