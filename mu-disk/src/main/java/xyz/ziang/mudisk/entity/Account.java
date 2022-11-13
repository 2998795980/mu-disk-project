package xyz.ziang.mudisk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    // 用户Id（唯一） 格式：两个字母+6位数字
    private Integer id;
    // 用户账号（唯一） 格式：数字字母6-12位
    private String username;
    // 用户密码（加密） 数字字母符号6-12位
    private String password;
    // 用户邮箱
    private String email;
    // 用户状态 -1:删除 0：封禁 1：正常
    private Integer state;

    // 创建时间
    private LocalDateTime create_time;
    // 更新时间
    private LocalDateTime update_time;
}
