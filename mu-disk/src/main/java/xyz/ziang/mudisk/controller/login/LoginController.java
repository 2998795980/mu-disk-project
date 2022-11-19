package xyz.ziang.mudisk.controller.login;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.common.result.ApiResultCode;
import xyz.ziang.mudisk.controller.login.form.UserLoginForm;
import xyz.ziang.mudisk.exception.ServiceException;
import xyz.ziang.mudisk.service.RedisService;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 */
@RestController
public class LoginController {

    @Autowired
    RedisService redisService;

    /**
     * 登录
     * @return 结果
     */
    @PostMapping("login")
    public ApiResult<String> loginByUserName(UserLoginForm userForm, @RequestParam(defaultValue = "false") boolean rememberMe, HttpServletRequest request) throws ServiceException {
        int state = checkCode(userForm.getCode(),request);
        if (state != 0) {
            codeFail(state);
        }
        // 验证 账户密码
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(userForm.getUsername(),userForm.getPassword(),rememberMe));
                // 登录成功

        } catch (AuthenticationException e) {
            throw new AuthenticationException("登陆失败，请检查账号/密码");
        }
        return ApiResult.success();
    }

    public ApiResult<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ApiResult.success();
    }

    /**
     * 验证码失败信息
     * @param state 状态
     * @return string
     */
    public ApiResult<String> codeFail(Integer state) {
        switch (state) {
            case 1:return ApiResult.failed(ApiResultCode.CODE_IS_EMPTER);
            case 2:return ApiResult.failed(ApiResultCode.CODE_EXPIRE);
            case 3:return ApiResult.failed(ApiResultCode.CODE_ERROR);
        }
        return null;
    }

    /**
     * 判断验证码是否正确
     * @param userCode 验证码
     * @param request request请求
     * @return state
     */
    public int checkCode(String userCode,HttpServletRequest request) throws ServiceException {
        // 获取Redis内验证码
        String key = request.getHeader("Token-Code");
        if (StringUtils.isBlank(key)) {
            throw new ServiceException("key为空");
        }
        String code = (String)redisService.get(key);

        redisService.remove(key);
        // 验证码为空
        if (StringUtils.isBlank(userCode)) {
            return 1;
        }
        // 验证码过期
        if (StringUtils.isBlank(code)) {
            return 2;
        }
        // 验证码错误
        if (!StringUtils.equals(code,userCode)) {
            return 3;
        }
        return 0;
    }
}
