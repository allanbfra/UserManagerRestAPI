package com.challange.usermanagerrest.entrypoint.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    DataSource dataSource;

    @Test
    void getListOfusersWithConfigurablePageSize() throws Exception {
//        mockMvc.perform(get("/api/users?page=0&pageSize=2"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$._embedded.userList[0].name").value("Joao"))
//                .andExpect(jsonPath("$._embedded.userList[1].name").value("Maria"));
//
//        mockMvc.perform(get("/api/users?page=1&pageSize=3"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$._embedded.userList[0].name").value("Rafael"))
//                .andExpect(jsonPath("$._embedded.userList[1].name").value("Rosa"));

    }

    @Test
    void getSingleExistingUserByCpf() throws Exception {
//        mockMvc.perform(get("/api/users/114.993.220-11")).andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.cpf").value("114.993.220-11"))
//                .andExpect(jsonPath("$.name").value("Joao"));
    }


    @Test
    void getSingleNotExistingUserByCpf() throws Exception {

        // Get user with id=22
//        mockMvc.perform(get("/api/users/357.501.960-62"))
//                .andExpect(status().isNotFound());

    }

    @Test
    void getSingleuserByInvaliduserCpf() throws Exception {

//        // Get user with id=i
//        mockMvc.perform(get("/api/users/i"))
//                .andExpect(status().isBadRequest());
//
//        // Get user with id=*
//        mockMvc.perform(get("/api/users/*"))
//                .andExpect(status().isBadRequest());

    }

    @Test
    void createUser() throws Exception{
//
//        User user = new User("Gabriel", "197.578.470-70", Timestamp.from(Instant.now()));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(user);
//
//        // Create new user
//        mockMvc.perform(post("/api/users").content(json).characterEncoding("utf-8")
//                        .contentType("application/json"))
//                .andExpect(status().isCreated());

    }

    @Test
    void createUserWithDuplicateCpf() throws Exception{
//
//        User user = new User("Thamires", "409.936.760-65", Timestamp.from(Instant.now()));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(user);
//
//        // Create new user with duplicate cpf
//        mockMvc.perform(post("/api/users").content(json).characterEncoding("utf-8")
//                        .contentType("application/json"))
//                .andExpect(status().isConflict());

    }
}