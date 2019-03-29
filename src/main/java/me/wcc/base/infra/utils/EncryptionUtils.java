package me.wcc.base.infra.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import me.wcc.base.exception.EncryptionException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * <p>
 * 加解密工具类
 * </p>
 */
@SuppressWarnings("all")
public class EncryptionUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    private EncryptionUtils() {
    }

    /**
     * MD5 非对称加密
     */
    public static class MD5 {
        /**
         * MD5 加密
         *
         * @param content 加密内容
         * @return 加密结果
         */
        public String encrypt(String content) {
            return DigestUtils.md5Hex(content);
        }

        /**
         * MD5 加密
         *
         * @param content 加密内容
         * @return 加密结果
         */
        public String encrypt(byte[] content) {
            return DigestUtils.md5Hex(content);
        }

        /**
         * MD5 加密
         *
         * @param contentStream 加密内容
         * @return 加密结果
         */
        public String encrypt(InputStream contentStream) {
            try {
                return DigestUtils.md5Hex(contentStream);
            } catch (IOException e) {
                throw new EncryptionException("MD5 encrypt failed!", e);
            }
        }
    }

    /**
     * AES 对称加密
     */
    public static class AES {
        private static final String ALGORITHM = "AES";

        /**
         * 生成秘钥
         */
        public static String generaterKey() {
            KeyGenerator keygen = null;
            try {
                keygen = KeyGenerator.getInstance(ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                throw new EncryptionException("AES generater Key failed!", e);
            }
            // 16 字节 == 128 bit
            keygen.init(128, new SecureRandom());
            SecretKey secretKey = keygen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }

        /**
         * 生成密钥
         */
        private static SecretKeySpec getSecretKeySpec(String secretKeyStr) {
            return new SecretKeySpec(Base64.getDecoder().decode(secretKeyStr), ALGORITHM);
        }

        /**
         * 加密
         */
        public static String encrypt(String content, String secretKey) {
            Key key = getSecretKeySpec(secretKey);
            try {
                // 创建密码器
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                // 初始化
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(DEFAULT_CHARSET)));
            } catch (Exception e) {
                throw new EncryptionException("AES encrypt failed!", e);
            }
        }

        /**
         * 解密
         */
        public static String decrypt(String content, String secretKey) {
            Key key = getSecretKeySpec(secretKey);
            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, key);
                return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
            } catch (Exception e) {
                throw new EncryptionException("AES decrypt failed!", e);
            }
        }
    }

    /**
     * RSA 对称加密
     */
    public static class RSA {
        private static final String ALGORITHM = "RSA";
        private static final String ALGORITHMS_SHA1 = "SHA1WithRSA";

        /**
         * 生成秘钥对
         *
         * @return first : 私钥/second : 公钥
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        public static Pair<String, String> generateKeyPair() {
            KeyPairGenerator keygen = null;
            try {
                keygen = KeyPairGenerator.getInstance(ALGORITHM);
            } catch (NoSuchAlgorithmException e) {
                throw new EncryptionException("RSA generate Key Pair failed!", e);
            }
            keygen.initialize(2048, new SecureRandom());
            // 生成密钥对
            KeyPair keyPair = keygen.generateKeyPair();
            return Pair.of(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()),
                    Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        }

        /**
         * 获取公钥
         *
         * @param publicKey 公钥
         * @return 公钥
         * @throws InvalidKeySpecException  无效的 Key Spec
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        public static RSAPublicKey getPublicKey(String publicKey) {
            try {
                return (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            } catch (Exception e) {
                throw new EncryptionException("RSA get Public Key failed!", e);
            }
        }

        /**
         * 获取私钥
         *
         * @param privateKey 私钥
         * @return 私钥
         * @throws InvalidKeySpecException  无效的 Key Spec
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        public static RSAPrivateKey getPrivateKey(String privateKey) {
            try {
                return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            } catch (Exception e) {
                throw new EncryptionException("RSA get Private Key failed!", e);
            }
        }

        /**
         * 私钥签名内容
         *
         * @param content    内容
         * @param privateKey 私钥
         * @return 私钥签名
         * @throws InvalidKeySpecException      无效的 Key Spec
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的编码
         * @throws SignatureException           签名异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static String sign(String content, String privateKey) {
            try {
                Signature signature = Signature.getInstance(ALGORITHMS_SHA1);
                signature.initSign(getPrivateKey(privateKey));
                signature.update(content.getBytes(DEFAULT_CHARSET));
                return Base64.getEncoder().encodeToString(signature.sign());
            } catch (Exception e) {
                throw new EncryptionException("RSA sign failed!", e);
            }
        }


        /**
         * 公钥校验签名
         *
         * @param content   内容
         * @param sign      签名
         * @param publicKey 公钥
         * @return 是否匹配
         * @throws InvalidKeySpecException      无效的 Key Spec
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的解码
         * @throws SignatureException           签名异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static boolean verify(String content, String sign, String publicKey) {
            try {
                Signature signature = Signature.getInstance(ALGORITHMS_SHA1);
                signature.initVerify(getPublicKey(publicKey));
                signature.update(content.getBytes(DEFAULT_CHARSET));
                return signature.verify(Base64.getDecoder().decode(sign));
            } catch (Exception e) {
                throw new EncryptionException("RSA verify failed!", e);
            }
        }

        /**
         * 使用公钥或者私钥加密
         *
         * @param content 内容
         * @param key     公钥或者私钥
         * @return 密文
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的解码
         * @throws BadPaddingException          错误间隔异常
         * @throws IllegalBlockSizeException    无效块大小异常
         * @throws NoSuchPaddingException       无效的监间距异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static String encrypt(String content, Key key) {
            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, key);
                return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(DEFAULT_CHARSET)));
            } catch (Exception e) {
                throw new EncryptionException("RSA encrypt failed!", e);
            }
        }

        /**
         * 使用公钥或者私钥解密
         *
         * @param content 内容
         * @param key     公钥或者私钥
         * @return 明文
         * @throws InvalidKeyException       无效的 Key
         * @throws BadPaddingException       错误间隔异常
         * @throws IllegalBlockSizeException 无效块大小异常
         * @throws NoSuchPaddingException    无效的监间距异常
         * @throws NoSuchAlgorithmException  无效的算法
         */
        public static String decrypt(String content, Key key) {
            try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, key);
                return new String(cipher.doFinal(Base64.getDecoder().decode(content)));
            } catch (Exception e) {
                throw new EncryptionException("RSA decrypt failed!", e);
            }
        }
    }

    /**
     * RSA2 对称加密
     */
    public static class RSA2 {
        private static final String ALGORITHMS_SHA256 = "SHA256WithRSA";

        /**
         * 生成秘钥对
         *
         * @return first : 私钥/second : 公钥
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        public static Pair<String, String> generateKeyPair() {
            return RSA.generateKeyPair();
        }

        /**
         * 获取公钥
         *
         * @param publicKey 公钥
         * @return 公钥
         * @throws InvalidKeySpecException  无效的 Key Spec
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        private static RSAPublicKey getPublicKey(String publicKey) {
            return RSA.getPublicKey(publicKey);
        }

        /**
         * 获取私钥
         *
         * @param privateKey 私钥
         * @return 私钥
         * @throws InvalidKeySpecException  无效的 Key Spec
         * @throws NoSuchAlgorithmException 找不到算法异常
         */
        private static RSAPrivateKey getPrivateKey(String privateKey) {
            return RSA.getPrivateKey(privateKey);
        }

        /**
         * 私钥签名内容
         *
         * @param content    内容
         * @param privateKey 私钥
         * @return 私钥签名
         * @throws InvalidKeySpecException      无效的 Key Spec
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的编码
         * @throws SignatureException           签名异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static String sign(String content, String privateKey) {
            try {
                Signature signature = Signature.getInstance(ALGORITHMS_SHA256);
                signature.initSign(getPrivateKey(privateKey));
                signature.update(content.getBytes(DEFAULT_CHARSET));
                return Base64.getEncoder().encodeToString(signature.sign());
            } catch (Exception e) {
                throw new EncryptionException("RSA2 sign failed!", e);
            }
        }


        /**
         * 公钥校验签名
         *
         * @param content   内容
         * @param sign      签名
         * @param publicKey 公钥
         * @return 是否匹配
         * @throws InvalidKeySpecException      无效的 Key Spec
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的解码
         * @throws SignatureException           签名异常
         * @throws NoSuchAlgorithmException     无效的算法
         * @throws NoSuchPaddingException       无效的监间距异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static boolean verify(String content, String sign, String publicKey) {
            try {
                Signature signature = Signature.getInstance(ALGORITHMS_SHA256);
                signature.initVerify(getPublicKey(publicKey));
                signature.update(content.getBytes(DEFAULT_CHARSET));
                return signature.verify(Base64.getDecoder().decode(sign));
            } catch (Exception e) {
                throw new EncryptionException("RSA2 verify failed!", e);
            }
        }

        /**
         * 使用公钥或者私钥加密
         *
         * @param content 内容
         * @param key     公钥或者私钥
         * @return 密文
         * @throws InvalidKeyException          无效的 Key
         * @throws UnsupportedEncodingException 不支持的解码
         * @throws BadPaddingException          错误间隔异常
         * @throws IllegalBlockSizeException    无效块大小异常
         * @throws NoSuchPaddingException       无效的监间距异常
         * @throws NoSuchAlgorithmException     无效的算法
         */
        public static String encrypt(String content, Key key) {
            return RSA.encrypt(content, key);
        }

        /**
         * 使用公钥或者私钥解密
         *
         * @param content 内容
         * @param key     公钥或者私钥
         * @return 明文
         * @throws InvalidKeyException       无效的 Key
         * @throws BadPaddingException       错误间隔异常
         * @throws IllegalBlockSizeException 无效块大小异常
         * @throws NoSuchPaddingException    无效的监间距异常
         * @throws NoSuchAlgorithmException  无效的算法
         */
        public static String decrypt(String content, Key key) {
            return RSA.decrypt(content, key);
        }
    }
}
