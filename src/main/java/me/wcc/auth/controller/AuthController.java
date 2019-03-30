package me.wcc.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wcc.auth.domain.entity.User;
import me.wcc.auth.service.UserService;
import me.wcc.base.controller.BaseController;
import me.wcc.base.infra.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chuncheng.wang@hand-china.com 19-3-27 上午10:22
 */
@RestController
@Api(tags = "认证接口")
@RequestMapping("/user")
public class AuthController extends BaseController {
    @Autowired
    UserService userService;

    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public ResponseEntity<User> current() {
        return Results.success(super.getCurrentUser());
    }

}
