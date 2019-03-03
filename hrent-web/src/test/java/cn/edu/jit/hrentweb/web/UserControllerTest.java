package cn.edu.jit.hrentweb.web;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import cn.edu.jit.hrentweb.HrentWebApplication;
import cn.edu.jit.hrentweb.model.entity.User;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private User user;

    private Gson gson;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        user = new User();
        user.setNickname("test_nickname");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws Exception {
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(gson.toJson(user, User.class)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void findOne() throws Exception {
        mockMvc.perform(get("/user/test_nickname")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteOne() throws Exception {
        mockMvc.perform(delete("/user/test_nickname"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}