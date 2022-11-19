package xyz.ziang.mudisk;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

public class HashMD5 {

    @Test
    void hashMD5() {
        SimpleHash simpleHash = new SimpleHash("md5", "root", UUID.randomUUID(), 10);
        System.out.println(simpleHash.toHex());
    }

    @Test
    void uuid() {
//        for (int i = 0; i < 10; i++) {
//            UUID uuid = UUID.randomUUID();
//            System.out.println(uuid.toString());
//        }

        System.out.println(cn.hutool.core.lang.UUID.fastUUID().toString());

        System.out.println(cn.hutool.core.lang.UUID.randomUUID().toString());

//        System.out.println(cn.hutool.core.lang.UUID.randomUUID(true).toString());

//        System.out.println(cn.hutool.core.lang.UUID.fromString("root").toString());

        System.out.println(cn.hutool.core.lang.UUID.randomUUID().hashCode());


    }

    @Test
    void salt() {
        String s = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println(s);
    }

    @Test
    void time() {
        System.out.println(LocalDateTime.now());
    }
}
