package xyz.ziang.mudisk.controller.login;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.common.result.ApiResult;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CaptchaController {

    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) {
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 存储验证码

        // 输出验证码
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            lineCaptcha.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
