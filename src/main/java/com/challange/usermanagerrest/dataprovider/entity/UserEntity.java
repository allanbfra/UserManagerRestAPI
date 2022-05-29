package com.challange.usermanagerrest.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Basic
    @Column(name = "birthday", nullable = false)
    private Timestamp birthday;
}

