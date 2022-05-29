package com.challange.usermanagerrest.dataprovider.repository;

import com.challange.usermanagerrest.dataprovider.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByCpf(String cpf);

    Page<UserEntity> findAll(Pageable page);
}
