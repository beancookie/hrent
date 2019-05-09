package cn.edu.jit.web.service;

import cn.edu.jit.web.model.dto.UserDTO;
import cn.edu.jit.web.model.entity.User;

/**
 * @author LuZhong
 */
public interface EmailService {
    /**
     * 发送邮件
     *
     * @param toUser
     */
    void sendCode(User toUser);

    boolean verifyCode(UserDTO user);
}
