package xyz.ziang.mudisk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import xyz.ziang.mudisk.common.entity.MpBaseEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person extends MpBaseEntity {
    private String account;
    private String username;
    private String email;
    private String phone;
    private Integer sex;
    private String picture;

    public Person(String account,String username,String email,String phone) {
        this.account = account;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.sex = 0;
        this.setCreatedBy(0L);
        this.setCreatedTime(LocalDateTime.now());
    }

    public Person(String account,String username,String email) {
        this.account = account;
        this.username = username;
        this.email = email;
        this.sex = 0;
        this.setCreatedBy(0L);
        this.setCreatedTime(LocalDateTime.now());
    }
}
