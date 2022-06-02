package com.challange.usermanagerrest.configuration;

import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import com.challange.usermanagerrest.dataprovider.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.time.Instant;

@Configuration
@Profile("!test")
public class InitiateDatabaseRecords {
    private static final Logger log = LoggerFactory.getLogger(InitiateDatabaseRecords.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        if(repository.findAll().size()<1) {
            return args -> {
                try {
                    repository.save(new UserEntity("Joao", "114.993.220-11", Timestamp.from(Instant.now())));
                    repository.save(new UserEntity("Maria", "277.879.110-85", Timestamp.from(Instant.now())));
                    repository.save(new UserEntity("Pedro", "672.321.030-18", Timestamp.from(Instant.now())));
                    repository.save(new UserEntity("Rafael", "967.410.090-35", Timestamp.from(Instant.now())));
                    repository.save(new UserEntity("Rosa", "539.952.180-67", Timestamp.from(Instant.now())));
                    repository.save(new UserEntity("Thamires", "409.936.760-65", Timestamp.from(Instant.now())));

                    log.info("Initial records inserted into database." );
                } catch (Exception e) {
                    log.info("Error in initiating records into database." );
                }
            };
        }
        else
            return args -> {
                log.info("Records already initiated ");
            };
    }
}
