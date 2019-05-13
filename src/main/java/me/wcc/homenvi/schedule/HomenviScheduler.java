package me.wcc.homenvi.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@Component
public class HomenviScheduler {
    private HomenviJobs homenviJobs;

    @Autowired
    public void setHomenviJobs(HomenviJobs homenviJobs) {
        this.homenviJobs = homenviJobs;
    }

    /**
     * 每天晚上6点生成一天的日报
     */
    @Scheduled(cron = "0 0 18 * * ?")
    public void dayReport() {
        homenviJobs.doDayReport();
    }

    /**
     * 每周日早上10点产生周报
     */
    @Scheduled(cron = "0 0 10 ? * SUN")
    public void weekReport() {
        homenviJobs.doWeekReport();
    }

    /**
     * 每月一日早上九点产生月报
     */
    @Scheduled(cron = "0 0 9 1 * ?")
    public void monthReport() {
        homenviJobs.doMonthReport();
    }

    /**
     * 每分钟查询一次前一分钟的平均值，达到指标通知用户（站内信和短信）。
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void generalWarning() {
        homenviJobs.doGeneralWarn();
    }
}
