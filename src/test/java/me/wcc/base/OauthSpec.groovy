package me.wcc.base

import me.wcc.homenvi.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author chuncheng.wang@hand-china.com 19-3-28 下午3:55
 */
@Transactional
@Rollback
@SpringBootTest(classes = Application.class)
@ContextConfiguration(loader = SpringBootContextLoader.class)
class OauthSpec extends Specification {
    @Autowired
    private WebApplicationContext context;
    @Shared
    boolean sharedSetupDone = false;
    @Shared
    private MockMvc mockMvc;

    void setup() {
        if (!sharedSetupDone) {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            sharedSetupDone = true;
        }
    }

    def "Test GET Token"() {
        given:
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>()
        params.put("grantType",Collections.singletonList("password"))
        params.put("clientId",Collections.singletonList("homenvi"))
        params.put("clientSecret",Collections.singletonList("homenvi123"))
        params.put("scope",Collections.singletonList("homenvi"))
        params.put("username",Collections.singletonList("admin"))
        params.put("password",Collections.singletonList("admin"))

        when:
        def result = mockMvc.perform(get("/oauth/token").params(params));

        then:
        result.andExpect(status().isOk())
        result.andExpect(content().string(containsString("access_token")));
    }
}