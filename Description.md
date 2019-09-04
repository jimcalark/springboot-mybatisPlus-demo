MyBatis plus 使用实践  mp 版本号： 3.2.0

一、搭建环境
    1、依赖：
        
     <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.2.0</version>
    </dependency>
    
   2、properties:
      
      mapper.xml文件地址配置：
            mybatis-plus.mapper-locations=classpath:com/mapper/*.xml
       
      debug 打印sql语句：
            logging.level.com.shame.remember.dao=debug
            `
   3、注意事项：
        
        引入 MyBatis-Plus 之后请不要再次引入 MyBatis 以及 MyBatis-Spring，以避免因版本差异导致的问题。
        
    
二、代码生成器
    参照官方文档例子实践  模板引擎使用freemarker
    
1、依赖：

a、代码生成器依赖 最新版本找官方文档示例
        
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.2.0</version>
        </dependency>
        
b、freemarker:
          
        <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>

2、示例代码： 项目中util包下  CodeGenerator 类

常用配置;
        
   a、数据库配置
           
            设置数据源url时：serverTimezone=GMT%2B8 为将时间改为东八区 %2B 为转移的 + 号
            jdbc:mysql://localhost:3306/mp?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8   
            
            关于schema  
                对于MySQL而言,“database 数据库” 和 “schema 模式” 是同一件事
                对于Oracle而言,schema == namespace within database, identical to user account

   b、配置项 
        
        没找到的实现;指定不生成某些文件
        
         
        stretegy:
              strategy.setTablePrefix("mp_");//生成的实体中不包含表的前缀  mp_
              strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");//父类地址
              strategy.setSuperEntityColumns("id"); //公共字段
              strategy.setInclude(tableName);//变长参数 需要生成的表名 strategy.setInclude(new String[] { rb.getString("tableName")}); // 需要生成的表
              strategy.setEntityBooleanColumnRemoveIsPrefix(false)//Boolean类型字段是否移除is前缀（默认 false）
              strategy.setLogicDeleteFieldName("enable");//逻辑删除的字段名
              strategy.setEntityTableFieldAnnotationEnable(true)//是否生成字段注解 默认true
              strategy.setVersionFieldName("version");//乐观锁字段
              
        gc:
              gc.setServiceName("%sService");//生成的service 名称前不加I
              gc.setOutputDir()//设置项目的绝对路径 默认 D://      
              gc.setSwagger2(true); 实体属性 Swagger2 注解
              gc.setBaseResultMap(true);// XML ResultMap
              gc.setFileOverride(false)//是否覆盖已有文件  默认fasle
              gc.setActiveRecord(true);//开启AR模式 默认false
              gc.setDateType(DateType.ONLY_DATE);//设置时间类 DateType枚举类 分别为uitl包,time包和 sql包。默认为time包
   
   c、字段填充
            
            生成器配置：
            
               List<TableFill> tableFillList = new ArrayList<>();
               tableFillList.add(new TableFill("creator_id", FieldFill.INSERT));
               tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
               tableFillList.add(new TableFill("modifier_id", FieldFill.UPDATE));
               tableFillList.add(new TableFill("modify_time", FieldFill.UPDATE));
               strategy.setTableFillList(tableFillList);
            
            配置类;
                MybatisPlusConfig 中的填充策略  其代码中使用的是属性非数据库字段 如 createTime 而非 create_time

三、性能检测 生产环境要去除

    1、依赖：
        
        <dependency>
          <groupId>p6spy</groupId>
          <artifactId>p6spy</artifactId>
          <version>3.8.0</version>
        </dependency>

    2、修改application.properties中mysql连接配置
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/remember?serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=UTF-8
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
    
    改为：
    使用p6spy的配置
    spring.datasource.url=jdbc:p6spy:mysql://127.0.0.1:3306/remember?serverTimezone=GMT%2B8&useSSL=false&useUnicode=true&characterEncoding=UTF-8
    spring.datasource.driverClassName=com.p6spy.engine.spy.P6SpyDriver
    
    3、新建配置文件：spy.properties
        内容如下：
            module.log=com.p6spy.engine.logging.P6LogFactory,com.p6spy.engine.outage.P6OutageFactory
            # 自定义日志打印
            logMessageFormat=com.baomidou.mybatisplus.extension.p6spy.P6SpyLogger
            #日志输出到控制台
            appender=com.baomidou.mybatisplus.extension.p6spy.StdoutLogger
            # 使用日志系统记录 sql
            #appender=com.p6spy.engine.spy.appender.Slf4JLogger
            # 设置 p6spy driver 代理
            deregisterdrivers=true
            # 取消JDBC URL前缀
            useprefix=true
            # 配置记录 Log 例外,可去掉的结果集有error,info,batch,debug,statement,commit,rollback,result,resultset.
            excludecategories=info,debug,result,batch,resultset
            # 日期格式
            dateformat=yyyy-MM-dd HH:mm:ss
            # 实际驱动可多个
            #driverlist=org.h2.Driver
            # 是否开启慢SQL记录
            outagedetection=true
            # 慢SQL记录标准 2 秒
            outagedetectioninterval=2

四、单表自定义返回对象的分页查询
    
        userController的pageUserList方法
        
        
五、未写实践的
    
        apply函数  nested函数  condition、  last、 allEq 、 inSql           
