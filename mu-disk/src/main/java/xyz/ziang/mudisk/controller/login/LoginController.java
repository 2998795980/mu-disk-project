package xyz.ziang.mudisk.controller.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.controller.login.form.UserLoginForm1;
import xyz.ziang.mudisk.controller.login.form.UserLoginForm2;

@RestController
public class LoginController {
    /**
     * 账号登录
     * @return 结果
     */
    @PostMapping("login/method1")
    public ApiResult<String> loginByUserName(UserLoginForm1 userForm) {
        return null;
    }

    /**
     * 邮箱登录
     * @return 结果
     */
    @PostMapping("login/method2")
    public ApiResult<String> loginByEmail(UserLoginForm2 userForm) {
        return null;
    }
}
