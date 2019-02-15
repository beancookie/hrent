package cn.edu.jit.hrentsearch.repository;

import cn.edu.jit.hrentsearch.model.entity.Ziroom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ZiroomRepositoryTest {
    @Autowired
    private ZiroomRepository ziroomRepository;

    @Test
    public void findAll() {

        List<Ziroom> ziroomList = ziroomRepository.findAll();
        log.info("{}", ziroomList.toString());
    }
}