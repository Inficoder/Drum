package com.bryce;

import cn.dev33.satoken.SaTokenManager;
import cn.dev33.satoken.spring.SaTokenSetup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SaTokenSetup
@SpringBootApplication
public class DrumApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrumApplication.class, args);
        log.info("启动成功：sa-token配置如下：" + SaTokenManager.getConfig());
    }
}
