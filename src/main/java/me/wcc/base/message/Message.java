package me.wcc.base.message;

import java.io.Serializable;

/**
 * 消息及类型
 *
 * @author bojiangzhou 2019/01/11
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 8091043474642466118L;
    public static final String FIELD_CODE = "code";
    public static final String FIELD_DESC = "desc";
    public static final String FIELD_TYPE = "type";
    public static final Type DEFAULT_TYPE = Type.WARN;

    private String code;

    private String desc;

    private String type;

    public Message() {
    }

    /**
     * 构建消息，默认消息类型为 警告(WARN) 基本
     */
    public Message(String code, String desc) {
        this.code = code;
        this.desc = desc;
        this.type = Message.DEFAULT_TYPE.code();
    }

    public Message(String code, String desc, Type type) {
        this.code = code;
        this.desc = desc;
        this.type = type.code();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("code='").append(code).append('\'');
        sb.append(", desc='").append(desc).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String code() {
        return code;
    }

    public Message setCode(String code) {
        this.code = code;
        return this;
    }

    public String desc() {
        return desc;
    }

    public Message setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String type() {
        return type;
    }

    public Message setType(String type) {
        this.type = type;
        return this;
    }

    public enum Type {

        INFO("info"),

        WARN("warn"),

        ERROR("error")

        ;

        private String code;

        Type(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }
    }
}
