package me.wcc.homenvi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wcc.base.controller.BaseController;
import me.wcc.base.infra.utils.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "开放测试接口")
@RestController
@RequestMapping("/test")
public class OpenController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(OpenController.class);

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

    @ApiOperation("模拟influxDB数据写入")
    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestBody String body, @RequestParam String db, @RequestParam String u,
                                        @RequestParam String p) {

        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        LOGGER.info("Content-Type:{}", request.getHeader("Content-Type"));
        LOGGER.info("db:{}", db);
        LOGGER.info("u:{}", u);
        LOGGER.info("p:{}",p);
        LOGGER.info("body:{}",body);
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return Results.success(body);
    }
}
