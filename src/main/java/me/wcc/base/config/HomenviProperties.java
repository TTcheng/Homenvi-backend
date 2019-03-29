package me.wcc.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * Application属性配置
 * </p>
 *
 * @author chuncheng.wang@hand-china.com 19-3-26 下午9:07
 */
@ConfigurationProperties(prefix = "homenvi")
public class HomenviProperties {
    private String secretKey = "c54a32d70ea36c118a651aec11fe747f";

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

