package cn.edu.jit.web.repository;

import cn.edu.jit.web.model.entity.House;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author LuZhong
 * @date 2019/3/1 14:11
 * @description
 */
public interface HouseRepository extends ReactiveMongoRepository<House, String> {
}
