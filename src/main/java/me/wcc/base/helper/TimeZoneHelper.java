package me.wcc.base.helper;

/**
 * author chuncheng.wang@hand-china.com 19-3-31 上午12:11
 */
public class TimeZoneHelper {
    private static final String DEFAULT_TIMEZONE = "GMT+8";
    private static ThreadLocal<String> timezones = new ThreadLocal<>();

    private TimeZoneHelper() {}

    public static String timezone() {
        String timezone = timezones.get();
        if (null == timezone) {
            timezone(DEFAULT_TIMEZONE);
            return DEFAULT_TIMEZONE;
        }
        return timezone;
    }

    public static void timezone(String lang) {
        timezones.set(lang);
    }
}
