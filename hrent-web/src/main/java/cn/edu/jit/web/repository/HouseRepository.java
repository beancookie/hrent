package cn.edu.jit.web.repository;

import cn.edu.jit.web.model.entity.House;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.Set;

/**
 * @author LuZhong
 * @date 2019/3/1 14:11
 * @description
 */
public interface HouseRepository extends ReactiveMongoRepository<House, String> {
    /**
     *
     * @param urls
     * @return
     */
    Flux<House> findByUrlIn(Collection<String> urls);
}
