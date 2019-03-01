package cn.edu.jit.hrentweb.repository;

import cn.edu.jit.hrentweb.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author LuZhong
 * @date 2019/3/1 14:11
 * @description
 */
public interface UserRepository extends ReactiveMongoRepository<User, Long> {
    /**
     * 根据昵称查询用户
     * @param nickname
     * @return
     */
    Mono<User> findByNickname(String nickname);

    /**
     * 根据昵称删除用户
     * @param nickname
     * @return
     */
    Mono<User> deleteByNickname(String nickname);
}
