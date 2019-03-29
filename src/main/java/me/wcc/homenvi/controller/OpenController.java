package me.wcc.homenvi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class OpenController {

    @RequestMapping("/hello")
    public String test() {
        return "Hello Homenvi";
    }
}
