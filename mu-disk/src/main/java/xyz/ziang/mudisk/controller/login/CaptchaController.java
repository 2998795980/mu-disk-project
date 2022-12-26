package xyz.ziang.mudisk.controller.login;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.service.RedisService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码控制器
 */
@RestController
@RequestMapping("captcha")
public class CaptchaController {

    @Autowired
    RedisService redisService;

    @GetMapping("base64")
    public ApiResult<String> base64(HttpServletRequest request, HttpServletResponse response) {
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 存储验证码
        saveCode(lineCaptcha.getCode(),request,response);
        // 输出验证码
        return ApiResult.success(lineCaptcha.getImageBase64Data());
    }

    @GetMapping("picture")
    public void picture(HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 存储验证码
        saveCode(lineCaptcha.getCode(),request,response);
        // 输出验证码
        lineCaptcha.write(response.getOutputStream());
    }

    public void saveCode(String code, HttpServletRequest request,HttpServletResponse response) {
        String uuid = UUID.randomUUID().toString();
        // 存入Redis
        redisService.setEx(uuid,code,60L);
        // 存入响应头
        response.setHeader("Token-Code",uuid);
    }
}
