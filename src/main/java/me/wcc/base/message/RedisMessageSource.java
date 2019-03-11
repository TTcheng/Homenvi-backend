package me.wcc.base.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wcc.base.helper.ApplicationContextHelper;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.redis.RedisHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.AbstractMessageSource;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;

import io.choerodon.mybatis.helper.LanguageHelper;

/**
 * 基于Redis的消息源
 *
 * @author bojiangzhou 2019/01/11
 */
public class RedisMessageSource extends AbstractMessageSource {

    private RedisHelper redisHelper;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageSource.class);

    private static final String MESSAGE_KEY = "message:";

    public RedisMessageSource() {
        redisHelper = ApplicationContextHelper.getContext().getBean(RedisHelper.class);
    }

    public RedisMessageSource(RedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    protected Message resolveMessage(String code, Object[] args, Locale locale) {
        return resolveMessage(code, args, null, locale);
    }

    protected Message resolveMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        Message message = getMessageObject(code, locale);
        String desc = null;
        if (message != null) {
            desc = message.desc();
        } else {
            try {
                desc = getParentMessageSource().getMessage(code, null, locale);
            } catch (NoSuchMessageException e) {
                LOGGER.warn("resolveMessage not found message for code={}", code);
            }
        }
        if (StringUtils.isBlank(desc) && StringUtils.isNotBlank(defaultMessage)) {
            desc = defaultMessage;
        }
        if (StringUtils.isNotBlank(desc) && ArrayUtils.isNotEmpty(args)) {
            desc = createMessageFormat(desc, locale).format(args);
        }
        if (StringUtils.isBlank(desc)) {
            desc = code;
        }

        String finalDesc = desc;
        message = Optional.ofNullable(message).map(m -> m.setDesc(finalDesc)).orElse(new Message(code, desc));

        return message;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        Message message = getMessageObject(code, locale);

        String msg = null;
        if (message != null) {
            msg = message.desc();
        } else {
            try {
                msg = getParentMessageSource().getMessage(code, null, locale);
            } catch (NoSuchMessageException e) {
                LOGGER.warn("resolveCode not found message for code={}", code);
            }
        }
        if (StringUtils.isNotBlank(msg)) {
            return createMessageFormat(msg, locale);
        }
        return null;
    }

    /**
     * 从redis中获取Message
     */
    private Message getMessageObject(String code, Locale locale) {
        String language;
        if (locale == null || StringUtils.isBlank(locale.toString())) {
            language = LanguageHelper.language();
        } else {
            language = locale.toString();
        }
        redisHelper.setCurrentDatabase(BaseConstants.REDIS_DB);
        String obj;
        try {
            obj = redisHelper.hshGet(MESSAGE_KEY + language, code);
        } finally {
            redisHelper.clearCurrentDatabase();
        }
        if (StringUtils.isNotEmpty(obj)) {
            try {
                return MAPPER.readValue(obj, Message.class);
            } catch (IOException e) {
                LOGGER.error("parse message error.{}", e.getMessage());
            }
        }
        return null;
    }


}
