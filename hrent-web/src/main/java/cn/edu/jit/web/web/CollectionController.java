package cn.edu.jit.web.web;

import cn.edu.jit.web.model.dto.UserDTO;
import cn.edu.jit.web.model.entity.House;
import cn.edu.jit.web.model.entity.User;
import cn.edu.jit.web.repository.HouseRepository;
import cn.edu.jit.web.repository.ReactiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author LuZhong
 * @date 2019/3/1 14:13
 * @description
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private ReactiveUserRepository userRepository;

    @Autowired
    private HouseRepository commodityRepository;

    /**
     *
     * @param user
     * @return
     */
    @PostMapping
    public Mono<ResponseEntity<User>> save(@RequestBody UserDTO user) {
        return userRepository.findById(user.getUserId())
                .flatMap(u -> {
                    u.getCollections().add(user.getCommodityId());
                    return userRepository.save(u);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     *
     * @param user
     * @return
     */
    @DeleteMapping
    public Mono<ResponseEntity<User>> deleteOne(@RequestBody UserDTO user) {
        return userRepository.findById(user.getUserId())
                .flatMap(u -> {
                    u.getCollections().remove(user.getCommodityId());
                    return userRepository.save(u);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public Flux<House> findByUserId(@PathVariable String userId) {
        User u = userRepository.findById(userId).block();
        return commodityRepository.findAllById(u.getCollections());
    }


}
