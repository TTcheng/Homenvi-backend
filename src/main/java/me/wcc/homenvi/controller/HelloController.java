package me.wcc.homenvi.controller;

import io.swagger.annotations.Api;
import me.wcc.base.message.MessageAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.choerodon.mybatis.helper.LanguageHelper;

/**
 * 测试
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午1:17
 */
@Api(tags = "测试接口")
@RequestMapping("/homenvi")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/msg")
    public String msg() {
        return MessageAccessor.getMessage("homenvi.hello", LanguageHelper.language()).desc();
    }
}
