package xyz.ziang.mudisk.controller.login;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ziang.mudisk.common.result.ApiResult;
import xyz.ziang.mudisk.common.result.ApiResultCode;
import xyz.ziang.mudisk.controller.login.form.UserRegisterForm;
import xyz.ziang.mudisk.entity.Account;
import xyz.ziang.mudisk.entity.Person;
import xyz.ziang.mudisk.exception.ServiceException;
import xyz.ziang.mudisk.mapper.AccountMapper;
import xyz.ziang.mudisk.mapper.PersonMapper;
import xyz.ziang.mudisk.mapper.RoleMapper;
import xyz.ziang.mudisk.service.RedisService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

/**
 * 注册控制器
 */
@RestController
public class RegisterController {

    @Autowired
    AccountMapper accountMapper;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    RedisService redisService;
    @Autowired
    RoleMapper roleMapper;

    @PostMapping("register")
    public ApiResult<String> register(UserRegisterForm userRegisterForm, HttpServletRequest request) throws ServiceException {
        int state = checkCode(userRegisterForm.getCode(),request);
        if (state != 0) {
            codeFail(state);
        }
        // 创建新用户
        addUserAndPerson(userRegisterForm);

        return ApiResult.success();
    }

    public void addUserAndPerson(UserRegisterForm userRegisterForm) throws ServiceException {
        @Length(min = 6, max = 10) String username = userRegisterForm.getNikename();
        @Length(min = 6, max = 12) String account = userRegisterForm.getUsername();
        @Length(min = 6, max = 12) String password = userRegisterForm.getPassword();
        @Length(min = 6, max = 12) String passwordtwo = userRegisterForm.getPasswordtwo();
        if(!StringUtils.equals(password,passwordtwo)) {
            throw new ServiceException("密码不一致");
        }
        @Email String email = userRegisterForm.getEmail();
        // 获取随机生成的salt
        String salt = getRandomSalt();
        // 加密密码
        password = new SimpleHash("md5",password,salt,10).toHex();
        // user表添加用户
        Account user = new Account(username, account, password, salt);
        accountMapper.insert(user);
        // person表添加个人信息
        Person person = new Person(username, account, email);
        personMapper.insert(person);
        // user_role添加角色
        if (!StringUtils.isBlank(userRegisterForm.getInvitationCode())) {
            roleMapper.addUserAdminRole(user.getId());
        }else{
            roleMapper.addUserRole(user.getId());
        }

    }

    public boolean isExistsInvitation(String invitation) {
        String code = (String) redisService.get(invitation);
        return !StringUtils.isBlank(code);
    }

    /**
     * 获取随机生成的salt
     * @return salt
     */
    public String getRandomSalt() {
        return new SecureRandomNumberGenerator().nextBytes().toHex();
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
    public int checkCode(String userCode, HttpServletRequest request) throws ServiceException {
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
