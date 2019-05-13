package me.wcc.homenvi.dao

import me.wcc.homenvi.Application
import me.wcc.homenvi.entity.HomenviCollections
import me.wcc.homenvi.utils.InfluxQueryHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class HomenviCollectionsDaoTest extends Specification {
    @Autowired
    HomenviCollectionsDao homenviCollectionsDao

    def "test queryForList"() {
        given:
        String sql = new InfluxQueryHelper()
                .select(HomenviCollections.FIELD_HUMIDITY, HomenviCollections.FILED_CELSIUS)
                .from(HomenviCollections.MEASUREMENT)
                .limit(10)
                .build()
        when:
        def list = homenviCollectionsDao.queryForList(sql)
        list.forEach({ data -> println(data) })
        then:
        list != null
        list.size() > 0
    }
}
