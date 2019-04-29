package me.wcc.base.exception;

import me.wcc.base.message.Message;
import me.wcc.base.message.MessageAccessor;

/**
 * 异常信息对象
 *
 * @author wuguokai
 */
public class ExceptionResponse {

    private Boolean failed;
    private String code;
    private String message;
    private String type;


    public ExceptionResponse() {

    }

    /**
     * 创建MessageDto对象
     *
     * @param message 提示消息
     */
    public ExceptionResponse(Boolean failed, String code, String message) {
        this.failed = failed;
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(String code) {
        this.failed = true;
        this.code = code;
        Message message = MessageAccessor.getMessage(code);
        this.message = message.desc();
        this.type = message.type();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFailed() {
        return failed;
    }

    public void setFailed(Boolean failed) {
        this.failed = failed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
