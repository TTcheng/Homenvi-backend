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
    private String influxDatabase;
    private String influxMeasurement;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getInfluxDatabase() {
        return influxDatabase;
    }

    public void setInfluxDatabase(String influxDatabase) {
        this.influxDatabase = influxDatabase;
    }

    public String getInfluxMeasurement() {
        return influxMeasurement;
    }

    public void setInfluxMeasurement(String influxMeasurement) {
        this.influxMeasurement = influxMeasurement;
    }
}

