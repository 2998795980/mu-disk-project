package xyz.ziang.mudisk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.ziang.mudisk.mapper")
public class MuDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuDiskApplication.class, args);
    }

}
