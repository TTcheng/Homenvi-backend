package me.wcc.base.infra.utils

import spock.lang.Specification

/**
 * @author chuncheng.wang@hand-china.com 19-3-26 下午8:49
 */
class EncryptionUtilsTest extends Specification {
    void generateKey() {
        given:
        def md5 = new EncryptionUtils.MD5()
        def cypher = "c54a32d70ea36c118a651aec11fe747f"
        def plain = "Homenvi"
        when:
        def encrypt = md5.encrypt(plain)
        then:
        cypher == encrypt
    }

    void "AES encrypt&decrypt"() {
        given:
        def key = "c54a32d70ea36c118a651aec11fe747f"
        def plaintext = "admin"
        when:
        def cyphered = EncryptionUtils.AES.encrypt(plaintext, key)
        then:
        plaintext == EncryptionUtils.AES.decrypt(cyphered, key)
    }
}
