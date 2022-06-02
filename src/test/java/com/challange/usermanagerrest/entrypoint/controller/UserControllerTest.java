package com.challange.usermanagerrest.entrypoint.controller;


import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import com.challange.usermanagerrest.dataprovider.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
//@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DataSource dataSource;

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
    void getListOfusersWithConfigurablePageSize() throws Exception {
        mockMvc.perform(get("/api/users?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data[0].name").value("Joao"))
                .andExpect(jsonPath("$.data[1].name").value("Maria"));

        mockMvc.perform(get("/api/users?page=1&pageSize=3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data[0].name").value("Rafael"))
                .andExpect(jsonPath("$.data[1].name").value("Rosa"))
                .andExpect(jsonPath("$.data[2].name").value("Thamires"));


    }

    @Test
    void getSingleExistingUserByCpf() throws Exception {
        mockMvc.perform(get("/api/users/by_cpf?cpf=114.993.220-11"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.data.cpf").value("114.993.220-11"))
                .andExpect(jsonPath("$.data.name").value("Joao"));
    }


    @Test
    void getSingleNotExistingUserByCpf() throws Exception {

        mockMvc.perform(get("/api/users/by_cpf?cpf=357.501.960-62"))
                .andExpect(status().isNotFound());

    }

    @Test
    void createUser() throws Exception{

        UserEntity user = new UserEntity("Gabriel", "197.578.470-70", Timestamp.from(Instant.now()));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/users").content(json).characterEncoding("utf-8")
                        .contentType("application/json"))
                .andExpect(status().isCreated());

    }

    @Test
    void createUserWithDuplicateCpf() throws Exception{

        UserEntity user = new UserEntity("Thamires", "409.936.760-65", Timestamp.from(Instant.now()));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        // Create new user with duplicate cpf
        mockMvc.perform(post("/api/users").content(json).characterEncoding("utf-8")
                        .contentType("application/json"))
                .andExpect(status().isConflict());

    }
}