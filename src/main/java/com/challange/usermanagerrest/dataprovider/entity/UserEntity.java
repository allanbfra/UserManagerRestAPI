package com.challange.usermanagerrest.dataprovider.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

