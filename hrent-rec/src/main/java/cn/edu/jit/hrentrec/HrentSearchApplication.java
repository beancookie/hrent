package cn.edu.jit.hrentrec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LuZhong
 */
@EnableDiscoveryClient
@SpringBootApplication
public class HrentSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrentSearchApplication.class, args);
    }

}

