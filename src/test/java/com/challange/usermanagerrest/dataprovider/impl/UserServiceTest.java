package com.challange.usermanagerrest.dataprovider.impl;

import com.challange.usermanagerrest.core.model.User;
import com.challange.usermanagerrest.core.service.UserService;
import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import com.challange.usermanagerrest.dataprovider.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
public class UserServiceTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserService userService;

    @BeforeEach
    private void setUp(){
        userRepository.save(new UserEntity("Joao", "114.993.220-11", Timestamp.from(Instant.now())));
        userRepository.save(new UserEntity("Maria", "277.879.110-85", Timestamp.from(Instant.now())));
        userRepository.save(new UserEntity("Pedro", "672.321.030-18", Timestamp.from(Instant.now())));
        userRepository.save(new UserEntity("Rafael", "967.410.090-35", Timestamp.from(Instant.now())));
        userRepository.save(new UserEntity("Rosa", "539.952.180-67", Timestamp.from(Instant.now())));
        userRepository.save(new UserEntity("Thamires", "409.936.760-65", Timestamp.from(Instant.now())));
    }

    @AfterEach
    private void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void createUser() {
        User user = new User("Leila","452.256.680-89",Timestamp.from(Instant.now()));
        User user2 = userService.saveUser(user);

        assertNotEquals(null, user,"Create new user didn't work");
        assertEquals("Leila", user2.getName(),"createUser didn't work (Name is not set)");
        assertEquals("452.256.680-89", user2.getCpf(),"createUser  didn't work (Cpf is not set)");
    }


    @Test
    public void getExistingUserByCpf() {

        User user = userService.getUserByCpf("114.993.220-11");
        assertNotEquals(null, user,"getUserByCpf didn't work correctly when user exists");
        assertEquals("Joao", user.getName(),"getUserByCpf didn't work correctly when user exists (Name is not set)");
    }

    @Test
    public void getNotExistingUserByCpf() {

        User user = userService.getUserByCpf("452.256.680-89");
        assertEquals(null, user,"getUserByCpf didn't work correctly when user doesn't exist");
    }

    @Test
    public void listUsers()
    {

        List<User> userList = userService.listUsers(0,3);
        assertEquals(3, userList.size(), "listUsers didn't retrieved correct number of records");
        assertEquals("Joao", userList.get(0).getName(), "listUsers didn't retrieved correct records");
        assertEquals("Maria", userList.get(1).getName(), "listUsers didn't retrieved correct records");

    }
}
