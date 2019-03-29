package me.wcc.base.exception;

/**
 * 加密异常
 */
public class EncryptionException extends RuntimeException {

    private static final long serialVersionUID = 8312873915138382557L;

    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
