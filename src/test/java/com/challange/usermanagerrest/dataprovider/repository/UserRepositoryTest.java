package com.challange.usermanagerrest.dataprovider.repository;

import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

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
    void findAllWithSpecifPageNumberAndPageSize() {
        List<UserEntity> listusers = userRepository.findAll(PageRequest.of(0, 2, Sort.by("name").ascending())).toList();
        assertEquals(listusers.size(), 2,"FindAll didn't retrieved correct number of records");
        assertEquals(listusers.get(0).getName(), "Joao","FindAll didn't retrieved correct records");
        assertEquals(listusers.get(1).getName(), "Maria","FindAll didn't retrieved correct records");

    }

    @Test
    public void findUserByCpf() {

        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByCpf("967.410.090-35"));
        assertEquals(true, user.isPresent(),"Find user didn't work");
        assertEquals("Rafael", user.get().getName(),"Find user didn't work");
    }

    @Test
    public void saveNewUser() {
        UserEntity user = new UserEntity("Mario","452.256.680-89",Timestamp.from(Instant.now()));
        userRepository.save(user);

        Optional<UserEntity> user2 = Optional.ofNullable(userRepository.findByCpf("452.256.680-89"));
        assertEquals(true, user2.isPresent(),"Save new user didn't work");
        assertEquals("Mario", user2.get().getName(),"Save new user didn't work");
    }
}