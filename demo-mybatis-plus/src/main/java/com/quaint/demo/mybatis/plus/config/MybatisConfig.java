package com.quaint.demo.mybatis.plus.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author quaint
 * @date 2019-12-30 15:19
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        // 逻辑删除相关, 配置逻辑删除后, mybatis 查询时 会自动拼接 valid = 1
        return new LogicSqlInjector();
    }

}
