package me.wcc.homenvi;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author chuncheng.wang@hand-china.com 19-3-6 上午9:22
 */
@EnableSwagger2Doc
@EnableAuthorizationServer
@SpringBootApplication(scanBasePackages = {"me.wcc.auth", "me.wcc.base", "me.wcc.homenvi"})
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
