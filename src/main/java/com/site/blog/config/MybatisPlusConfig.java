package com.site.blog.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @classname: MybatisPlusConfig
 * @description: mybatis-plus配置
 * @author: 南街
 * @create: 2019-05-25 09:58
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.site.blog.dao")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
