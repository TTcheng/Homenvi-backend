package me.wcc.iems.controller;

import me.wcc.base.message.MessageAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import io.choerodon.mybatis.helper.LanguageHelper;

/**
 * 测试
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午1:17
 */
@RequestMapping("/iems")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/msg")
    public String msg() {
        String arg1 = " IEMS";
        return MessageAccessor.getMessage("iems.hello", LanguageHelper.language()).desc();
    }
}
