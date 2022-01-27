package com.corneliu.registration.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "AUTHENTICATION_TOKENS")
public class AuthenticationToken {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TOKEN", unique = true, nullable = false)
    private String token;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private UserEntity user;

}
