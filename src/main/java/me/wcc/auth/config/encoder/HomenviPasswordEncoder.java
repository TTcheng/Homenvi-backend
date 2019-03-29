package me.wcc.auth.config.encoder;

import me.wcc.base.config.HomenviProperties;
import me.wcc.base.infra.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密器
 *
 * @author chuncheng.wang@hand-china.com 19-3-28 下午11:04
 */
public class HomenviPasswordEncoder implements PasswordEncoder {
    @Autowired
    HomenviProperties properties;

    @Override
    public String encode(CharSequence rawPassword) {
        return EncryptionUtils.AES.encrypt(rawPassword.toString(), properties.getSecretKey());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawPasswordEncoded = encode(rawPassword);
        return null != rawPasswordEncoded && rawPasswordEncoded.equals(encodedPassword);
    }
}
