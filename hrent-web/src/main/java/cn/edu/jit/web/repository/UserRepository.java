package cn.edu.jit.web.repository;

import cn.edu.jit.web.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author LuZhong
 * @date 2019/3/1 14:11
 * @description
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * 根据昵称查询用户
     * @param nickname
     * @return
     */
    Optional<User> findByNickname(String nickname);

    /**
     * 根据昵称删除用户
     * @param nickname
     * @return
     */
    Optional<User> deleteByNickname(String nickname);

    /**
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    /**
     *
     * @param mobile
     * @return
     */
    Optional<User> findByMobile(String mobile);

    /**
     *
     * @param email
     * @return
     */
    Optional<User> findByEmailAndPassword(String email, String password);

    /**
     *
     * @param mobile
     * @return
     */
    Optional<User> findByMobileAndPassword(String mobile, String password);

}
