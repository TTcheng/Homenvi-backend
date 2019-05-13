package me.wcc.homenvi.service

import me.wcc.homenvi.Application
import me.wcc.homenvi.entity.CollectionSpecification
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
class CollectionSpecificationServiceTest extends Specification {
    @Autowired
    CollectionSpecificationService collectionSpecificationService

    def "test selectMapByFields"() {
        given:
        def fields = Arrays.asList("humidity", "celsius")
        when:
        def map = collectionSpecificationService.selectMapByFields(fields)
        then:
        map != null
        map.size() > 0
        map.forEach({ key, value -> println(value) })
    }
}
