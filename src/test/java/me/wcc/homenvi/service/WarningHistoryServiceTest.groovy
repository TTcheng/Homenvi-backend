package me.wcc.homenvi.service

import me.wcc.homenvi.Application
import me.wcc.homenvi.entity.WarningHistory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime

/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class WarningHistoryServiceTest extends Specification {
    @Autowired
    WarningHistoryService warningHistoryService

    def "test selectCountFromTime"() {
        given:
        def anHourAgo = Instant.now().minusSeconds(3600)
        def condition = new WarningHistory(5L)
        condition.setCreationDate(Date.from(anHourAgo))
        when:
        def count = warningHistoryService.selectCountFromTime(condition)
        then:
        null != count
    }
}
