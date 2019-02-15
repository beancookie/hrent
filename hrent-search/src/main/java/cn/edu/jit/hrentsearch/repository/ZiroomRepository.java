package cn.edu.jit.hrentsearch.repository;

import cn.edu.jit.hrentsearch.model.entity.BaiXing;
import cn.edu.jit.hrentsearch.model.entity.Ziroom;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author LuZhong
 * @Date: 2019/2/14 13:10
 * @Description:
 */
public interface ZiroomRepository extends MongoRepository<Ziroom, String> {
}
