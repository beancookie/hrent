package cn.edu.jit.hrentweb.service;

/**
 * @author luzhong
 */
public interface EmailService {
    /**
     * 发送邮件
     * @param toUser
     * @param subject
     * @param content
     */
    void sendCode(String toUser, String subject, int code);
}
