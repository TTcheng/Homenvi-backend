package me.wcc.base.service

import io.choerodon.mybatis.pagehelper.domain.PageRequest
import io.choerodon.mybatis.pagehelper.domain.Sort
import me.wcc.homenvi.Application
import me.wcc.homenvi.entity.Notification
import me.wcc.homenvi.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification


/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
@Transactional
@Rollback
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class BaseServiceSpec extends Specification {

    @Autowired
    NotificationService notificationService

    void "pageAndSort"() {
        given:
        Notification condition = new Notification()
        Long admin = 5L
        condition.setUserid(admin)

        PageRequest pageRequest = new PageRequest()
        pageRequest.setPage(0)
        pageRequest.setSize(10)
        Sort.Order ascendingOrder = new Sort.Order(Sort.Direction.ASC, Notification.FIELD_TITLE)
        pageRequest.setSort(new Sort(ascendingOrder))

        when: "ascending"
        def notifications = notificationService.pageAndSort(pageRequest, condition)

        then:
        println(notifications)
        for (int i = 1; i < notifications.size(); i++) {
            notifications.get(i).title < notifications.get(i - 1).title
        }

        when: "descending"
        Sort.Order descendingOrder = new Sort.Order(Sort.Direction.ASC, Notification.FIELD_TITLE)
        pageRequest.setSort(new Sort(descendingOrder))
        notificationService.pageAndSort(pageRequest, condition)

        then:
        println(notifications)
        for (int i = 1; i < notifications.size(); i++) {
            notifications.get(i).title > notifications.get(i - 1).title
        }
    }
}