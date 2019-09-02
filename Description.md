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

常见问题;
        
   a、数据库时区问题
           
            设置数据源url时：serverTimezone=GMT%2B8 为将时间改为东八区 %2B 为转移的 + 号
            
            jdbc:mysql://localhost:3306/mp?serverTimezone=GMT%2B8&useUnicode=true&useSSL=false&characterEncoding=utf8   
        
   b、数据源配置 schemaName, mysql数据不需要设置,
    
            关于schema  
            对于MySQL而言,“database 数据库” 和 “schema 模式” 是同一件事
            对于Oracle而言,schema == namespace within database, identical to user account
            
   c、不要前缀
        
              strategy.setTablePrefix("mp_");//生成的实体中不包含表的前缀  mp_
