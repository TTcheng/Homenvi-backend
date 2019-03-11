package me.wcc

import org.apache.commons.lang3.StringUtils
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import spock.lang.Specification


/**
 * <p>
 * todo
 * </p>
 *
 * @author chuncheng.wang@hand-china.com 19-3-11 下午4:37
 */
class CommonSpec extends Specification {
    public void stringJoin(){
        given:
        def message = "message"
        def colon = ":"
        def zh_cn = "zh_CN"
        def expect = "message:zh_CN"

        when:
        def res = StringUtils.join(message,colon,zh_cn)

        then:
        expect == res
    }

    void testResourceLoader(){
        given:
        ResourceLoader resourceLoader = new DefaultResourceLoader()


    }
}