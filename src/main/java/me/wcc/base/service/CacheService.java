package me.wcc.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.message.Message;
import me.wcc.base.redis.RedisHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * 缓存服务
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午4:17
 */
@Service
public class CacheService {

    private RedisHelper redisHelper;
    private ObjectMapper objectMapper;

    public CacheService(RedisHelper redisHelper, ObjectMapper objectMapper) {
        this.redisHelper = redisHelper;
        this.objectMapper = objectMapper;
    }

    /**
     * 缓存消息到redis
     */
    public void cacheMessage(Message message, Locale locale) {
        String uniqueKey = this.buildUniqueKey(locale);
        try {
            redisHelper.hshPut(uniqueKey, message.code(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量缓存到redis
     */
    public void cacheMessages(List<Message> messages, Locale locale) {
        messages.forEach(message -> this.cacheMessage(message, locale));
    }

    /**
     * 清除缓存
     */
    public void clearCache(Message message, Locale locale) {
        String uniqueKey = this.buildUniqueKey(locale);
        redisHelper.hshDelete(uniqueKey, message.code());
    }

    /**
     * 生成 redis 唯一键
     *
     * @return uniqueKey
     */
    private String buildUniqueKey(Locale locale) {
        // 使用 code 和 lang 组成缓存 hash 的唯一键
        return StringUtils.join(BaseConstants.FIELD_MSG, BaseConstants.Symbol.COLON, locale);
    }

}
