package cn.edu.jit.hrentweb.web;

import cn.edu.jit.hrentweb.model.entity.User;
import cn.edu.jit.hrentweb.repository.UserRepository;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author LuZhong
 * @date 2019/3/1 14:13
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存或更新用户
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     * @param user
     * @return
     */
    @PostMapping
    public Mono<User> save(@RequestBody User user) {
        return userRepository.save(user)
                .onErrorResume(e -> userRepository.findByNickname(user.getNickname())
                        .flatMap(originalUser -> {
                            user.setId(originalUser.getId());
                            return userRepository.save(user);
                        }));
    }

    @GetMapping("/{nickname}")
    public Mono<User> findByNickname(@PathVariable String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @GetMapping
    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{nickname}")
    public Mono<User> deleteOne(@PathVariable String nickname) {
        return userRepository.deleteByNickname(nickname);
    }


}
