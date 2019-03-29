package me.wcc.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author chuncheng.wang@hand-china.com 19-3-27 上午10:22
 */
@RestController
@Api(tags = "认证接口")
public class AuthController {

    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public Principal getUser(Principal principal) {
        return principal;
    }

}
