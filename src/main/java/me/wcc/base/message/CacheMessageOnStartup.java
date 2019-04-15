package me.wcc.base.message;

import me.wcc.base.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

/**
 * <p>
 * 创建ApplicationContext时缓存消息到Redis中
 * classpath:messages/messages_zh_CN.properties
 * classpath:messages/messages_en_US.properties
 * </p>
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午8:25
 */
@Component
public class CacheMessageOnStartup implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheMessageOnStartup.class);
    private static final String MESSAGES_EN = "messages/messages_en_US.properties";
    private static final String MESSAGES_ZH = "messages/messages_zh_CN.properties";
    private static final Locale ZH_CN = Locale.CHINA;
    private static final Locale EN_US = Locale.US;

    private CacheService cacheService;

    public CacheMessageOnStartup(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOGGER.info("缓存消息到Redis中");
        Properties messagesEn = new Properties();
        Properties messagesZh = new Properties();
        InputStream resourceInputStreamEn = CacheMessageOnStartup.class.getClassLoader().getResourceAsStream(MESSAGES_EN);
        InputStream resourceInputStreamZh = CacheMessageOnStartup.class.getClassLoader().getResourceAsStream(MESSAGES_ZH);
        try {
            messagesEn.load(resourceInputStreamEn);
        } catch (IOException e) {
            LOGGER.error("读取{}失败", MESSAGES_EN);
        }
        try {
            messagesZh.load(resourceInputStreamZh);
        } catch (IOException e) {
            LOGGER.error("读取{}失败", MESSAGES_ZH);
        }
        this.cacheMessages(messagesEn, EN_US);
        this.cacheMessages(messagesZh, ZH_CN);
    }

    @SuppressWarnings("unchecked")
    private void cacheMessages(Properties messages, Locale locale) {
        if (!messages.isEmpty()) {
            Enumeration<String> names = (Enumeration<String>) messages.propertyNames();
            while (names.hasMoreElements()) {
                String key = names.nextElement();
                String value = messages.getProperty(key);
                if (null != value) {
                    Message.Type type = Message.Type.INFO;
                    if (value.startsWith(Message.Type.ERROR.code())) {
                        type = Message.Type.ERROR;
                    }
                    if (value.startsWith(Message.Type.WARN.code())) {
                        type = Message.Type.WARN;
                    }
                    Message message = new Message(key, value, type);
                    cacheService.cacheMessage(message, locale);
                }
            }
        }
    }
}
