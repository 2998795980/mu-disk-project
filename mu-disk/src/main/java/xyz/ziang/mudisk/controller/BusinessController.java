package xyz.ziang.mudisk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.annotation.AutoIdempotent;
import xyz.ziang.mudisk.service.TokenService;

import javax.annotation.Resource;

@RestController
public class BusinessController {

    @Resource
    private TokenService tokenService;

    @GetMapping("/get/token")
    public String getToken() {
        return tokenService.createToken();
    }

    /**
     * 测试方法
     * @return ok
     */
    @AutoIdempotent
    @GetMapping("/test/idempotence")
    public String testIdempotence() {
        return "ok";
    }
}
