package me.wcc.homenvi.schedule;

import me.wcc.auth.entity.User;
import me.wcc.auth.service.UserService;
import me.wcc.base.service.MailService;
import me.wcc.homenvi.dao.HomenviCollectionsDao;
import me.wcc.homenvi.entity.CollectionWarning;
import me.wcc.homenvi.entity.HomenviCollections;
import me.wcc.homenvi.entity.WarningHistory;
import me.wcc.homenvi.service.CollectionWarningService;
import me.wcc.homenvi.service.HomenviCollectionsService;
import me.wcc.homenvi.service.NotificationService;
import me.wcc.homenvi.service.WarningHistoryService;
import me.wcc.homenvi.utils.InfluxQueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@Component
public class HomenviJobs {
    public static final Logger LOGGER = LoggerFactory.getLogger(HomenviJobs.class);

    private UserService userService;
    private HomenviCollectionsDao collectionsDao;
    private HomenviCollectionsService homenviCollectionsService;
    private CollectionWarningService collectionWarningService;
    private NotificationService notificationService;
    private MailService mailService;
    private WarningHistoryService warningHistoryService;

    @Autowired
    public void setHomenviCollectionsService(HomenviCollectionsService homenviCollectionsService) {
        this.homenviCollectionsService = homenviCollectionsService;
    }

    @Autowired
    public void setWarningHistoryService(WarningHistoryService warningHistoryService) {
        this.warningHistoryService = warningHistoryService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setCollectionWarningService(CollectionWarningService collectionWarningService) {
        this.collectionWarningService = collectionWarningService;
    }

    @Autowired
    public void setCollectionsDao(HomenviCollectionsDao collectionsDao) {
        this.collectionsDao = collectionsDao;
    }

    private static final Long ADMIN_ID = 0L;

    public void doDayReport() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastDay = now.minusDays(1);
        String report = homenviCollectionsService.htmlReport(lastDay, now);
        String content = "<p>今天是" + LocalDate.now().toString() + "，近一天来您的家庭室内环境状况如下:</p><br>" + report;
        sendReport(ADMIN_ID, "Homenvi日报", content);
    }

    public void doWeekReport() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastWeek = now.minusWeeks(1);
        String report = homenviCollectionsService.htmlReport(lastWeek, now);
        String content = "<p>今天是" + LocalDate.now().toString() + "，近一周来您的家庭室内环境状况如下:</p><br>" + report;
        sendReport(ADMIN_ID, "Homenvi周报", content);
    }

    public void doMonthReport() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastMonth = now.minusMonths(1);
        String report = homenviCollectionsService.htmlReport(lastMonth, now);
        String content = "<p>今天是" + LocalDate.now().toString() + "，近一月来您的家庭室内环境状况如下:</p><br>" + report;
        sendReport(ADMIN_ID, "Homenvi月报", content);
    }

    private void sendReport(Long userid, String title, String content) {
        notificationService.newNotification(userid, title, content);
        User admin = userService.selectInsensitiveUser(userid);
        mailService.sendHtmlMail(admin.getEmail(), title, content);
    }


    private final List<String> generalFields = Stream.of(
            HomenviCollections.FIELD_GAS,
            HomenviCollections.FIELD_HUMIDITY,
            HomenviCollections.FIELD_CELSIUS,
            HomenviCollections.FIELD_DUST_DENSITY,
            HomenviCollections.FIELD_SOUND
    ).map(field -> "mean(" + field + ") AS " + field).collect(Collectors.toList());
    private final String generalSql = new InfluxQueryHelper()
            .select(generalFields)
            .from(HomenviCollections.MEASUREMENT)
            .where("time>now()-1m")
            .build();

    public void doGeneralWarn() {
        List<HomenviCollections> collectionsList = collectionsDao.queryForList(generalSql);
        collectionsList.forEach(collection -> {
            Map<String, Double> values = collection.getValues();
            values.forEach((key, value) -> {
                if (value != null) {
                    List<CollectionWarning> collectionWarnings = collectionWarningService.select(new CollectionWarning(key));
                    for (CollectionWarning warning : collectionWarnings) {
                        if (warning.satisfy(value)) {
                            WarningHistory condition = new WarningHistory(warning.getId());
                            LocalDateTime anHourAgo = LocalDateTime.now().minusHours(1);
                            condition.setCreationDate(Date.from(anHourAgo.atZone(ZoneId.systemDefault()).toInstant()));
                            Integer count = warningHistoryService.selectCountFromTime(condition);
                            if (null != count && count > 0) {
                                LOGGER.debug("一小时内不再发起第二次通知");
                                return;
                            }
                            // generate new warning notification
                            String title = "Homenvi检测到" + warning.getCollectionName() + "达到【" + warning.getReason() + "】";
                            String content = buildHtmlWarnContent(warning);
                            warningHistoryService.newHistory(warning, content);
                            notificationService.newNotification(ADMIN_ID, title, content);
                            User admin = userService.selectInsensitiveUser(ADMIN_ID);
                            mailService.sendHtmlMail(admin.getEmail(), title, content);
                            break;
                        }
                    }
                }
            });
        });
    }

    private String buildHtmlWarnContent(CollectionWarning warning) {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("<strong>");
        contentBuilder.append(warning.getAdvice());
        contentBuilder.append("</strong>");
        contentBuilder.append("。<br>参考信息：<p>");
        contentBuilder.append(warning.getRemark());
        contentBuilder.append("</p>");
        return contentBuilder.toString();
    }
}
