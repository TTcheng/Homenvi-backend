package me.wcc.base

import me.wcc.auth.entity.User
import me.wcc.auth.mapper.UserMapper
import me.wcc.base.config.HomenviProperties
import me.wcc.base.infra.utils.EncryptionUtils
import me.wcc.homenvi.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification


/**
 * @author chuncheng.wang@hand-china.com 19-3-25 下午5:00
 */
@Transactional
@Rollback
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class UserMapperSpec extends Specification {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HomenviProperties homenviProperties

    void "查询用户"() {
        given:
        def name = "admin"
        when:
        List<User> admin = userMapper.select(new User(name))

        then:
        admin.size() == 1
        name == admin.get(0).getLoginName()
    }

    void "新增用户"() {
        given:
        def loginName = "admin"
        def password = loginName
        User user = new User()
        def cypher = EncryptionUtils.AES.encrypt(password, homenviProperties.getSecretKey())
        user.setLoginName(loginName)
        user.setEmail("admin@163.com")
        user.setPassword(cypher)
        user.setRealName("Jesse")
        user.setPhone("15884955091")

        when:
        userMapper.insertSelective(user)
        def storedUser = userMapper.selectByPrimaryKey(user.getId())

        then:
        storedUser.getLoginName() == loginName
    }
}