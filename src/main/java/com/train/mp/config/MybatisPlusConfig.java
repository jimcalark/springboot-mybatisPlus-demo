package com.train.mp.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis plus 配置类
 *
 * @author Jim Clark
 * @version 1.0
 * create on  2019/9/4 0004 10:14
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     *
     * @return
     */
    @Bean//纳入spring 容器页
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
