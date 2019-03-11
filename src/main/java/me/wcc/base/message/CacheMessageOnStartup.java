package me.wcc.base.message;

import me.wcc.base.service.CacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * <p>
 * 加载classpath:messages/messages.properties到内存中
 * </p>
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午8:25
 */
@Component
public class CacheMessageOnStartup implements ApplicationListener<ApplicationStartedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheMessageOnStartup.class);

    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
    private CacheService cacheService;

    public CacheMessageOnStartup(CacheService cacheService) {
        this.cacheService = cacheService;
    }


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOGGER.info("缓存消息到Redis中");
        // todo 缓存消息到redis中
        resourceLoader.getResource("classpath:messages/messages");

//        Map<String,String> messages =
//        cacheService.cacheMessage();
    }
}
