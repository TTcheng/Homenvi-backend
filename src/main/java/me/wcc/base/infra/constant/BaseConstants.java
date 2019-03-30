package me.wcc.base.infra.constant;

import java.util.Locale;

/**
 * 基础常量
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午2:29
 */
public class BaseConstants {
    public static final Integer FLAG_YES = 1;
    public static final Integer FLAG_NO = 0;
    /**
     * 默认页码
     */
    public static final String PAGE = "0";
    /**
     * 默认页面大小
     */
    public static final String SIZE = "10";

    /**
     * 默认页码字段名
     */
    public static final String PAGE_FIELD_NAME = "page";
    /**
     * 默认页面大小字段名
     */
    public static final String SIZE_FIELD_NAME = "size";
    /**
     * 默认页码
     */
    public static final Integer PAGE_NUM = 0;
    /**
     * 默认页面大小
     */
    public static final Integer PAGE_SIZE = 10;
    /**
     * body
     */
    public static final String FIELD_BODY = "body";
    /**
     * KEY content
     */
    public static final String FIELD_CONTENT = "content";
    /**
     * 默认语言
     */
    public static final Locale DEFAULT_LOCALE = Locale.CHINA;
    /**
     * 默认语言
     */
    public static final String DEFAULT_LOCALE_STR = Locale.CHINA.toString();
    /**
     * KEY message
     */
    public static final String FIELD_MSG = "message";
    /**
     * KEY failed
     */
    public static final String FIELD_FAILED = "failed";
    /**
     * KEY success
     */
    public static final String FIELD_SUCCESS = "success";
    /**
     * KEY errorMsg
     */
    public static final String FIELD_ERROR_MSG = "errorMsg";
    /**
     * 默认编码
     */
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final Integer REDIS_DB = 0;

    /**
     * 基础异常编码
     */
    public static final class ErrorCode {
        /**
         * 数据校验不通过
         */
        public static final String DATA_INVALID = "error.data_invalid";
        /**
         * 资源不存在
         */
        public static final String NOT_FOUND = "error.not_found";
        /**
         * 程序出现错误，请联系管理员
         */
        public static final String ERROR = "error.error";
        /**
         * 网络异常，请稍后重试
         */
        public static final String ERROR_NET = "error.network";
        /**
         * 记录不存在或版本不一致
         */
        public static final String OPTIMISTIC_LOCK = "error.optimistic_lock";
        /**
         * 数据已存在，请不要重复提交
         */
        public static final String DATA_EXISTS = "error.data_exists";
        /**
         * 数据不存在
         */
        public static final String DATA_NOT_EXISTS = "error.data_not_exists";
        /**
         * 资源禁止访问
         */
        public static final String FORBIDDEN = "error.forbidden";
        /**
         * 数据库异常：编码重复
         */
        public static final String ERROR_CODE_REPEAT = "error.code_repeat";
        /**
         * 数据库异常：编号重复
         */
        public static final String ERROR_NUMBER_REPEAT = "error.number_repeat";
        /**
         * SQL执行异常
         */
        public static final String ERROR_SQL_EXCEPTION = "error.sql_exception";
        /**
         * 请登录后再进行操作！
         */
        public static final String NOT_LOGIN = "error.not_login";
        /**
         * 不能为空
         */
        public static final String NOT_NULL = "error.not_null";
        /**
         * 用户名或密码错误
         */
        public static final String WRONG_PWD_OR_NAME = "error.auth.wrong_password_or_username";
        /**
         * 内部错误
         */
        public static final String INTERNAL_ERROR = "error.internal_error";
        /**
         * 不支持的认证错误
         */
        public static final String UNKNOWN_AUTH_ERROR = "error.auth.unknown_authentication_error";
        public static final String FIELD_NOT_NULL = "error.base.field_not_null";

        private ErrorCode() {
        }
    }

    /**
     * 日期时间匹配格式
     */
    public static final class Pattern {
        //
        // 常规模式
        // ----------------------------------------------------------------------------------------------------
        /**
         * yyyy-MM-dd
         */
        public static final String DATE = "yyyy-MM-dd";
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
        /**
         * yyyy-MM-dd HH:mm
         */
        public static final String DATETIME_MM = "yyyy-MM-dd HH:mm";
        /**
         * yyyy-MM-dd HH:mm:ss.SSS
         */
        public static final String DATETIME_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
        /**
         * HH:mm
         */
        public static final String TIME = "HH:mm";
        /**
         * HH:mm:ss
         */
        public static final String TIME_SS = "HH:mm:ss";

        //
        // 系统时间格式
        // ----------------------------------------------------------------------------------------------------
        /**
         * yyyy/MM/dd
         */
        public static final String SYS_DATE = "yyyy/MM/dd";
        /**
         * yyyy/MM/dd HH:mm:ss
         */
        public static final String SYS_DATETIME = "yyyy/MM/dd HH:mm:ss";
        /**
         * yyyy/MM/dd HH:mm
         */
        public static final String SYS_DATETIME_MM = "yyyy/MM/dd HH:mm";
        /**
         * yyyy/MM/dd HH:mm:ss.SSS
         */
        public static final String SYS_DATETIME_SSS = "yyyy/MM/dd HH:mm:ss.SSS";

        //
        // 无连接符模式
        // ----------------------------------------------------------------------------------------------------
        /**
         * yyyyMMdd
         */
        public static final String NONE_DATE = "yyyyMMdd";
        /**
         * yyyyMMddHHmmss
         */
        public static final String NONE_DATETIME = "yyyyMMddHHmmss";
        /**
         * yyyyMMddHHmm
         */
        public static final String NONE_DATETIME_MM = "yyyyMMddHHmm";
        /**
         * yyyyMMddHHmmssSSS
         */
        public static final String NONE_DATETIME_SSS = "yyyyMMddHHmmssSSS";

        /**
         * EEE MMM dd HH:mm:ss 'CST' yyyy
         */
        public static final String CST_DATETIME = "EEE MMM dd HH:mm:ss 'CST' yyyy";

        //
        // 数字格式
        // ------------------------------------------------------------------------------
        /**
         * 无小数位 0
         */
        public static final String NONE_DECIMAL = "0";
        /**
         * 一位小数 0.0
         */
        public static final String ONE_DECIMAL = "0.0";
        /**
         * 两位小数 0.00
         */
        public static final String TWO_DECIMAL = "0.00";
        /**
         * 千分位表示 无小数 #,##0
         */
        public static final String TB_NONE_DECIMAL = "#,##0";
        /**
         * 千分位表示 一位小数 #,##0.0
         */
        public static final String TB_ONE_DECIMAL = "#,##0.0";
        /**
         * 千分位表示 两位小数 #,##0.00
         */
        public static final String TB_TWO_DECIMAL = "#,##0.00";

        private Pattern() {
        }
    }

    /**
     * 符号常量
     */
    public static final class Symbol {
        /**
         * 感叹号：!
         */
        public static final String SIGH = "!";
        /**
         * 符号：@
         */
        public static final String AT = "@";
        /**
         * 井号：#
         */
        public static final String WELL = "#";
        /**
         * 美元符：$
         */
        public static final String DOLLAR = "$";
        /**
         * 人民币符号：￥
         */
        public static final String RMB = "￥";
        /**
         * 空格：
         */
        public static final String SPACE = " ";
        /**
         * 换行符：\r\n
         */
        public static final String LB = System.getProperty("line.separator");
        /**
         * 百分号：%
         */
        public static final String PERCENTAGE = "%";
        /**
         * 符号：&
         */
        public static final String AND = "&";
        /**
         * 星号
         */
        public static final String STAR = "*";
        /**
         * 中横线：-
         */
        public static final String MIDDLE_LINE = "-";
        /**
         * 下划线：_
         */
        public static final String LOWER_LINE = "_";
        /**
         * 等号：=
         */
        public static final String EQUAL = "=";
        /**
         * 加号：+
         */
        public static final String PLUS = "+";
        /**
         * 冒号：:
         */
        public static final String COLON = ":";
        /**
         * 分号：;
         */
        public static final String SEMICOLON = ";";
        /**
         * 逗号：,
         */
        public static final String COMMA = ",";
        /**
         * 点号：.
         */
        public static final String POINT = ".";
        /**
         * 斜杠：/
         */
        public static final String SLASH = "/";
        /**
         * 双斜杠：//
         */
        public static final String DOUBLE_SLASH = "//";
        /**
         * 反斜杠
         */
        public static final String BACKSLASH = "\\";
        /**
         * 问号：?
         */
        public static final String QUESTION = "?";
        /**
         * 左花括号：{
         */
        public static final String LEFT_BIG_BRACE = "{";
        /**
         * 右花括号：}
         */
        public static final String RIGHT_BIG_BRACE = "}";
        /**
         * 左中括号：[
         */
        public static final String LEFT_MIDDLE_BRACE = "[";
        /**
         * 右中括号：[
         */
        public static final String RIGHT_MIDDLE_BRACE = "]";
        /**
         * 反引号：`
         */
        public static final String BACKQUOTE = "`";

        private Symbol() {
        }
    }
    private BaseConstants() {
    }
}
