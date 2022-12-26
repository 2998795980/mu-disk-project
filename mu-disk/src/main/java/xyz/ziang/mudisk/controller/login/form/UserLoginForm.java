package xyz.ziang.mudisk.controller.login.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * 登录表单  账号登录 格式验证
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginForm {
    // 账号
    private String username;
    // 邮箱
    private String email;
    // 手机号
    private String phone;
    // 密码
    private String password;
    // 验证码
    private String code;
}
