package me.wcc.base

import me.wcc.base.config.HomenviProperties
import me.wcc.homenvi.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification


/**
 * @author chuncheng.wang@hand-china.com 19-3-26 下午9:46
 */
@Transactional
@Rollback
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class SpringBootSpec extends Specification {
    @Autowired
    HomenviProperties homenviProperties

    void "Homenvi properties"(){
        def key = homenviProperties.getSecretKey()

        expect:
        key == "c54a32d70ea36c118a651aec11fe747f"
    }
}