package io.choerodon.mybatis.helper;

public class LanguageHelper {
    private static final String DEFAULT_LANG = "zh_CN";
    private static ThreadLocal<String> languages = new ThreadLocal<>();

    private LanguageHelper() {

    }

    /**
     * 根据当前登陆用户获取语言信息
     *
     * @return String
     */
    public static String language() {
        /**
         * CustomUserDetails details = UserDetailsHelper.getUserDetails();
         *         if (details != null) {
         *             language(details.getLanguage());
         *         } else {
         *             if (languages.get() == null) {
         *                 language("zh_CN");
         *                 LOGGER.warn("principal not instanceof CustomUserDetails language is zh_CN");
         *             }
         *         }
         *         return languages.get();
         */
        String lang = languages.get();
        if (null == lang){
            language(DEFAULT_LANG);
            return DEFAULT_LANG;
        }
        return lang;
    }

    public static void language(String lang) {
        languages.set(lang);
    }
}
