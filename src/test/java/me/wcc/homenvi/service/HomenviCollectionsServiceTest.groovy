package me.wcc.homenvi.service

import me.wcc.homenvi.Application
import me.wcc.homenvi.dao.HomenviCollectionsDao
import me.wcc.homenvi.entity.HomenviCollections
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.CollectionUtils
import spock.lang.Specification

import java.time.LocalDateTime

/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class HomenviCollectionsServiceTest extends Specification {
    @Autowired
    HomenviCollectionsService service

    def "test getMeanByTimeRange"() {
        given:
        def end = LocalDateTime.now()
        def start = end.minusMinutes(1)
        when:
        def collections = service.getMeanByTimeRange(start, end)
        then:
        println(collections.toString())
        noExceptionThrown()
        collections != null
        !CollectionUtils.is(collections.getValues())
    }
}
