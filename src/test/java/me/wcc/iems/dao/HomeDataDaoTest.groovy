package me.wcc.iems.dao

import me.wcc.iems.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * @author chuncheng.wang@hand-china.com 19-3-6 上午10:07
 */
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class HomeDataDaoTest extends Specification {
    @Autowired
    TestTableDao tableDao;

    def "testBaseMapper"() {
        given:
        def id = 1;
        when:
        def test = tableDao.selectByPrimaryKey(id);
        then:
        println(test)
    }
}
