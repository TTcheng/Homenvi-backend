package me.wcc.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.wcc.auth.domain.CustomUserDetails;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.message.Message;
import me.wcc.base.redis.RedisHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * 缓存服务
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午4:17
 */
@Service
public class CacheService {
    private static final long DEFAULT_USER_EXPIRE = 60 * 30L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

    private RedisHelper redisHelper;
    private ObjectMapper objectMapper;

    public CacheService(RedisHelper redisHelper, ObjectMapper objectMapper) {
        this.redisHelper = redisHelper;
        this.objectMapper = objectMapper;
    }

    public void cacheCustomUserDetails(CustomUserDetails userDetails) {
        try {
            redisHelper.hshPut(CustomUserDetails.CLASS_NAME, userDetails.getUsername(),
                            objectMapper.writeValueAsString(userDetails));
            redisHelper.setExpire(CustomUserDetails.CLASS_NAME, DEFAULT_USER_EXPIRE);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomUserDetails(CustomUserDetails userDetails) {
        redisHelper.hshDelete(CustomUserDetails.CLASS_NAME, userDetails);
    }

    public CustomUserDetails getCustomUserDetails(String loginName) {
        redisHelper.setCurrentDatabase(BaseConstants.REDIS_DB);
        String obj;
        try {
            obj = redisHelper.hshGet(CustomUserDetails.CLASS_NAME, loginName);
        } finally {
            redisHelper.clearCurrentDatabase();
        }
        if (StringUtils.isNotEmpty(obj)) {
            try {
                return objectMapper.readValue(obj, CustomUserDetails.class);
            } catch (IOException e) {
                // TODO 修复反序列化失败的问题
                LOGGER.error("parse message error.{}", e.getMessage());
            }
        }
        return null;
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
    public void clearMessage(Message message, Locale locale) {
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
