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
public class UserLoginForm1 {
    @Length(min = 6,max = 12)
    private String username;
    @Length(min = 6,max = 12)
    private String password;
    @Length(min = 5,max = 5)
    private String code;
}
