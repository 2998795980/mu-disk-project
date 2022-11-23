package xyz.ziang.mudisk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xyz.ziang.mudisk.common.entity.MpBaseEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user")
public class Account extends MpBaseEntity {

    // 用户名  格式：长度6-18
    private String username;
    // 用户账号（唯一） 格式：数字字母6-12位
    private String account;
    // 用户密码（加密） 数字字母符号6-12位
    private String password;
    // 用户邮箱
    private String email;
    // 用户手机号
    private String phone;
    // 加密 salt
    private String salt;
    // 用户状态 -1:删除 0：封禁 1：正常
    private Integer state;
    // 权限:游客，用户，管理员
    private String permission;

    public Account(String username, String account, String password, String salt, String phone) {
        this.username = username;
        this.account = account;
        this.password = password;
        this.salt = salt;
        this.phone = phone;
        this.state = 1;
        this.setCreatedBy(0L);
        this.setCreatedTime(LocalDateTime.now());
    }

    public Account(String username, String account, String password, String salt) {
        this.username = username;
        this.account = account;
        this.password = password;
        this.salt = salt;
        this.state = 1;
        this.setCreatedBy(0L);
        this.setCreatedTime(LocalDateTime.now());
    }
}
