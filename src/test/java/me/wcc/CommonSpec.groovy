package me.wcc

import me.wcc.auth.entity.User
import org.apache.commons.lang3.StringUtils
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification


/**
 * @author chuncheng.wang@hand-china.com 19-3-11 下午4:37
 */
class CommonSpec extends Specification {
    void "test entityClass"(){
        when:
        Class<User> entityClass;
        def instance = entityClass.newInstance()
        then:
        instance != null


    }

    void "test my encoder"(){
        given:
        def encoder = new BCryptPasswordEncoder()
        def password = "homenvi123"

        when:
        def encode = encoder.encode(password)

        then:
        println(encode)
        encoder.matches(password, encode)
    }

    void "test encoder"() {
        given:
        def encoder = new BCryptPasswordEncoder()
        def password = "sdfafafsf"
        def admin = "admin"

        when:
        def encode = encoder.encode(password)
        def adminEncode = encoder.encode(admin)

        then:
        encoder.matches(admin, adminEncode)
        encoder.matches(password, encode)

    }

    void stringJoin() {
        given:
        def message = "message"
        def colon = ":"
        def zh_cn = "zh_CN"
        def expect = "message:zh_CN"

        when:
        def res = StringUtils.join(message, colon, zh_cn)

        then:
        expect == res
    }

    void testResourceLoader() {
        given:
        ResourceLoader resourceLoader = new DefaultResourceLoader()


    }
}