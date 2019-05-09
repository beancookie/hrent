package cn.edu.jit.web.service.impl;

import cn.edu.jit.web.model.dto.UserDTO;
import cn.edu.jit.web.model.entity.User;
import cn.edu.jit.web.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author LuZhong
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public String fromUser;

    @Override
    public void sendCode(User toUser) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromUser);
            helper.setTo(toUser.getEmail());
            helper.setSubject("帮您租注册验证码");
            String code = UUID.randomUUID().toString().substring(0, 6);
            redisTemplate.opsForValue().set(toUser.getEmail(), code, 60, TimeUnit.SECONDS);
            String sb = "您在帮您租注册验证码为" + code;
            helper.setText(sb);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyCode(UserDTO user) {
        String verifyCode = (String) redisTemplate.opsForValue().get(user.getEmail());
        if (null != verifyCode && verifyCode.equals(user.getCode())) {
            return redisTemplate.delete(user.getEmail());
        }
        return false;
    }
}
