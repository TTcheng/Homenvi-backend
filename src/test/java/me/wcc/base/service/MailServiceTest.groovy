package me.wcc.base.service

import me.wcc.homenvi.Application
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
class MailServiceTest extends Specification {
    @Autowired
    MailService mailService

    def "test sendSimpleMail"() {
        given:
        String to = "ttchengwang@foxmail.com"
        when:
        mailService.sendSimpleMail(to, "测试发送简单邮件", "简单邮件内容")
        then:
        noExceptionThrown()
    }

    def "test sendHtmlMail"() {
        given:
        String to = "ttchengwang@foxmail.com"
        when:
        mailService.sendHtmlMail(to, "测试发送html邮件", "<p><strong>HTML</strong>邮件内容</p>")
        then:
        noExceptionThrown()
    }
}
