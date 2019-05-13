package me.wcc.quartz

import me.wcc.homenvi.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.text.SimpleDateFormat
import java.time.Instant


/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class QuartzSpec extends Specification {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzSpec.class)

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private static final String DEFAULT_LOG = "default"
    static String latestLog = DEFAULT_LOG

    void "HelloJob"() {
        Thread.sleep(2100)
        expect:
        latestLog != DEFAULT_LOG
        latestLog.endsWith("Hello Job")
    }

    /**
     * 每两秒打印一次Hello Job
     */
    @Scheduled(cron = "0/2 * * * * ? ")
    void helloPer2sec() {
        def dateTime = DATE_FORMAT.format(Instant.now().toEpochMilli())
        LOGGER.info("{}: Hello Job", dateTime)
        latestLog = dateTime + ": Hello 6:00AM"
    }

    @Scheduled(cron = "0 0 6 * * ?")
    void hello6am() {
        def dateTime = DATE_FORMAT.format(Instant.now().toEpochMilli())
        LOGGER.info("{}: Hello 6:00AM", dateTime)
        latestLog = dateTime + ": Hello 6:00AM"
    }
}