package cn.edu.jit.web.web;

import cn.edu.jit.web.model.dto.UserDTO;
import cn.edu.jit.web.model.entity.User;
import cn.edu.jit.web.repository.UserRepository;
import cn.edu.jit.web.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author LuZhong
 * @date 2019/3/1 14:13
 * @description
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;


    /**
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDTO user) {
        if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getMobile())) {
            return ResponseEntity.notFound().build();
        }
        if (StringUtils.isNotEmpty(user.getEmail())) {
            // 邮箱登录
            Optional<User> optionalUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (optionalUser.isPresent()) {
                return ResponseEntity.ok(optionalUser.get());
            }
        } else if (StringUtils.isNotEmpty(user.getMobile())) {
            // 手机号登录
            Optional<User> optionalUser = userRepository.findByMobileAndPassword(user.getMobile(), user.getPassword());
            if (optionalUser.isPresent()) {
                return ResponseEntity.ok(optionalUser.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        if (users.size() > 0) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/code")
    public ResponseEntity<String> register(@RequestBody User user, HttpServletRequest request) {
        if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getMobile())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("手机号或邮箱不能为空");
        }

        if (StringUtils.isNotEmpty(user.getEmail())) {
            // 邮箱注册
            Optional<User> originalUser = userRepository.findByEmail(user.getEmail());
            if (originalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("邮箱已被他人注册");
            } else {
                emailService.sendCode(user);
                return ResponseEntity.ok("发送邮箱验证码成功");
            }
        } else if (StringUtils.isNotEmpty(user.getMobile())) {
            // 手机号注册
            Optional<User> originalUser = userRepository.findByMobile(user.getMobile());
            if (originalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("手机号已被他人注册");
            } else {
                return ResponseEntity.ok("发送短信验证码成功");
            }
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerVerify(@RequestBody UserDTO user) {
        if (StringUtils.isNotEmpty(user.getEmail())) {
            if (emailService.verifyCode(user)) {
                return ResponseEntity.ok(userRepository.save(user));
            } else {
                return new ResponseEntity("验证码不正确", HttpStatus.BAD_REQUEST);
            }
        } else if (StringUtils.isNotEmpty(user.getMobile())) {
            return new ResponseEntity("暂不支持短信验证", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.badRequest().build();
    }
}
