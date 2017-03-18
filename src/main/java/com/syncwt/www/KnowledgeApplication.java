package com.syncwt.www;

import com.syncwt.www.config.KnowledgeProperties;
import com.syncwt.www.config.WechatConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@MapperScan("com.syncwt.www.dao")
@SpringBootApplication
@EnableConfigurationProperties({KnowledgeProperties.class, WechatConfig.class})
@EnableDiscoveryClient
@RefreshScope
public class KnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeApplication.class, args);
    }

}
