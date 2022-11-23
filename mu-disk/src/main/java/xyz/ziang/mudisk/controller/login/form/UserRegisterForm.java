package xyz.ziang.mudisk.controller.login.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

/**
 * 用户 注册表单
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterForm {
    // 用户名
    @Length(min = 6,max = 12)
    private String username;
    // 密码
    @Length(min = 6,max = 12)
    private String password;
    // 确认密码
    @Length(min = 6,max = 12)
    private String passwordtwo;
    // 邮箱
    @Email
    private String email;
    @Length(min = 11, max = 11)
    private String phone;
    // 昵称 6-10位
    @Length(min = 6,max = 10)
    private String nikename;
    // 验证码
    @Length(min = 5,max = 5)
    private String code;
    // 邀请码 用来注册admin管理用户
    @Length(min = 36)
    private String invitationCode;

}
