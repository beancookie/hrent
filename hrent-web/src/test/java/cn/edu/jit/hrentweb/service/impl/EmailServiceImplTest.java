package cn.edu.jit.hrentweb.service.impl;

import cn.edu.jit.hrentweb.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {
    @Autowired
    private EmailService emailService;

    @Test
    public void send() {
        emailService.sendCode("1531047764@qq.com", "货比三家注册邮件", 153785);
    }
}