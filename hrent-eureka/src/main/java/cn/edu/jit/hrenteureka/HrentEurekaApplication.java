package cn.edu.jit.hrenteureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LuZhong
 */
@EnableEurekaServer
@SpringBootApplication
public class HrentEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrentEurekaApplication.class, args);
    }

}

