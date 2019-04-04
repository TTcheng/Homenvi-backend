package me.wcc.auth.exception;

import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.message.MessageAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 异常转换器
 */
@Component
public class HomenviWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomenviWebResponseExceptionTranslator.class);

    private static final String BAD_CREDENTIALS = "Bad credentials";

    /**
     * 处理用户认证异常(认证主体 user details)
     *
     * @param e spring security内部异常
     * @return 经过处理的异常信息
     * @throws Exception 通用异常
     */
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        LOGGER.error("Grant Error :", e);
        OAuth2Exception oAuth2Exception;
        if (e instanceof InvalidGrantException) {
            String errorMsg = e.getMessage();
            if (BAD_CREDENTIALS.equals(errorMsg)) {
                errorMsg = MessageAccessor.getMessage(BaseConstants.ErrorCode.WRONG_PWD_OR_NAME).desc();
            }
            oAuth2Exception = new InvalidGrantException(errorMsg, e);
        } else if (e instanceof InternalAuthenticationServiceException) {
            oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
        } else if (e instanceof OAuth2Exception) {
            oAuth2Exception = new OAuth2Exception(e.getMessage(), e);
        } else {
            String errorMsg = MessageAccessor.getMessage(BaseConstants.ErrorCode.UNKNOWN_AUTH_ERROR).desc();
            oAuth2Exception = new UnsupportedResponseTypeException(errorMsg, e);
        }
        return super.translate(oAuth2Exception);
    }
}
