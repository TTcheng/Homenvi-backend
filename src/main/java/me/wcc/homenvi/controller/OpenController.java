package me.wcc.homenvi.controller;

import io.swagger.annotations.Api;
import me.wcc.base.infra.utils.Results;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "开放测试接口")
@RestController
@RequestMapping("/test")
public class OpenController {

    @RequestMapping("/hello")
    public String test() {
        return "Hello Homenvi";
    }

    @RequestMapping("/error")
    public ResponseEntity<String> error() {
        return Results.error("Hello Error");
    }

    @RequestMapping("/204")
    public ResponseEntity successNoContent() {
        return Results.success();
    }

    @RequestMapping("/200")
    public ResponseEntity<String> success() {
        return Results.success("Hello world");
    }
}
