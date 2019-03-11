package me.wcc.base.config;

import me.wcc.base.helper.ApplicationContextHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基本配置
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午3:23
 */
@Configuration
public class BaseConfiguration {

    @Bean
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}
