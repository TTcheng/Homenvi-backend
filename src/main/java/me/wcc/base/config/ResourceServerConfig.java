package me.wcc.base.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring4all.swagger.SwaggerAutoConfiguration;
import me.wcc.base.infra.serial.DateDeserializer;
import me.wcc.base.infra.serial.DateSerializer;
import me.wcc.base.message.Message;
import me.wcc.base.message.convert.MessageDeserializer;
import me.wcc.base.message.convert.MessageSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Date;

@Configuration
@EnableResourceServer
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@AutoConfigureAfter(SwaggerAutoConfiguration.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 要正常运行，需要反注释掉这段，具体原因见下面分析
     * 这里设置需要token验证的url
     * 这些需要在WebSecurityConfigurerAdapter中排查掉
     * 否则优先进入WebSecurityConfigurerAdapter,进行的是basic auth或表单认证,而不是token认证
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/oauth/**", "/test/**", "/iems/**", "/openApi/**")
                .and()
                .authorizeRequests().antMatchers("/iems/**").authenticated()
                .and()
                .authorizeRequests().antMatchers("/oauth/**").permitAll();
    }

    /**
     * 创建 jackson objectMapper bean
     *
     * @return 返回bean
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(Date.class, new DateSerializer());
        javaTimeModule.addDeserializer(Date.class, new DateDeserializer());
        mapper.registerModule(javaTimeModule);
        SimpleModule messageModule = new SimpleModule();
        messageModule.addDeserializer(Message.class, new MessageDeserializer());
        messageModule.addSerializer(Message.class, new MessageSerializer());
        mapper.registerModule(messageModule);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    /**
     * messageBean配置文件
     *
     * @return Bean
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle =
                new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }

}