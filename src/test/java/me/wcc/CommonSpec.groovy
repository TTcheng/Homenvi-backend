package me.wcc

import me.wcc.auth.entity.User
import org.apache.commons.lang3.StringUtils
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset


/**
 * @author chuncheng.wang@hand-china.com 19-3-11 下午4:37
 */
class CommonSpec extends Specification {

    void "test localDateTime"() {
        when:
        LocalDateTime dateTime = LocalDateTime.now()
        Instant now = dateTime.toInstant(ZoneOffset.of("+8"))
        then:
        ZoneOffset.systemDefault().getId()
        println(LocalDate.now().toString())
        println(now)
        println(now.atZone(ZoneOffset.systemDefault()).toInstant())
        println(ZoneOffset.systemDefault())
        noExceptionThrown()
    }

    void "map foreach"() {
        when:
        Map<String, String> map = new HashMap<>()
        map.put("jesse", "wong")
        map.put("morgan", "king")
        map.forEach({ key, value ->
            print(key)
            print("=")
            println(value)
        })
        then:
        noExceptionThrown()
    }

    void "Instant"() {
        when:
        Instant now = Instant.now()
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        System.out.println("现在的时间是：" + sf.format(now.toEpochMilli()))
        then:
        noExceptionThrown()
    }

    void "string length"() {
        String data = "humidity=56.80,celsius=29.50,fahrenheit=85.10,heatIndexCelsius=31.36,heatIndexFahrenheit=88.45,sound=515,brightness=18,dustDensity=145.59,gasValue=377";
        println data.length()
        expect:
        data.length() > 0
    }

    void "test entityClass"() {
        when:
        Class<User> entityClass;
        def instance = entityClass.newInstance()
        then:
        instance != null


    }

    void "test my encoder"() {
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