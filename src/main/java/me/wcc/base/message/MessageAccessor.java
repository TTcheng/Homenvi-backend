package me.wcc.base.message;

import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import io.choerodon.mybatis.helper.LanguageHelper;

/**
 * 首先从Redis缓存中获取消息，如果没有则从 classpath 下的 message.properties 里获取，使用者可提供默认消息。
 * 该消息存取器里的静态方法返回的 Message 对象不会为 null。
 *
 * @author bojiangzhou 2018/09/15
 * @see MessageSourceAccessor
 */
public class MessageAccessor {

    private static final RedisMessageSource REDIS_MESSAGE_SOURCE;
    private static final ReloadableResourceBundleMessageSource PARENT_MESSAGE_SOURCE;

    private MessageAccessor() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    static {
        PARENT_MESSAGE_SOURCE = new ReloadableResourceBundleMessageSource();
        PARENT_MESSAGE_SOURCE.setBasenames(
                "classpath:messages/messages",
                "classpath:messages/messages_core",
                "classpath:messages/messages_export",
                "classpath:messages/messages_ext");
        PARENT_MESSAGE_SOURCE.setDefaultEncoding("UTF-8");

        RedisMessageSource redisMessageSource = new RedisMessageSource();
        redisMessageSource.setParentMessageSource(PARENT_MESSAGE_SOURCE);
        redisMessageSource.setAlwaysUseMessageFormat(true);

        REDIS_MESSAGE_SOURCE = redisMessageSource;
    }

    /**
     * 添加资源文件位置
     *
     * @param names 如 <code>classpath:messages/messages_core<code/>
     */
    public static void addBasenames(String... names) {
        PARENT_MESSAGE_SOURCE.addBasenames(names);
    }

    /**
     * 覆盖默认资源文件位置
     *
     * @param names 如 <code>classpath:messages/messages_core<code/>
     */
    public static void setBasenames(String... names) {
        PARENT_MESSAGE_SOURCE.setBasenames(names);
    }

    public static Message getMessage(String code, String defaultMessage) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, null, defaultMessage, LocaleUtils.toLocale(LanguageHelper.language()));
    }

    public static Message getMessage(String code, String defaultMessage, Locale locale) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, null, defaultMessage, locale);
    }

    public static Message getMessage(String code, Object[] args, String defaultMessage) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, args, defaultMessage, LocaleUtils.toLocale(LanguageHelper.language()));
    }

    public static Message getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, args, defaultMessage, locale);
    }

    public static Message getMessage(String code) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, null, LocaleUtils.toLocale(LanguageHelper.language()));
    }

    public static Message getMessage(String code, Locale locale) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, null, locale);
    }

    public static Message getMessage(String code, Object[] args) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, args, LocaleUtils.toLocale(LanguageHelper.language()));
    }

    public static Message getMessage(String code, Object[] args, Locale locale) {
        return REDIS_MESSAGE_SOURCE.resolveMessage(code, args, locale);
    }

}
