package cn.edu.jit.web.repository;

import cn.edu.jit.web.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author LuZhong
 * @date 2019/3/1 14:11
 * @description
 */
public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {
}
