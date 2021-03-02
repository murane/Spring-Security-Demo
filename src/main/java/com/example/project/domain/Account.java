package com.example.project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name="ACCOUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "ACCOUNT_EMAIL",nullable = false,unique = true)
    private String email;

    @Column(name = "ACCOUNT_PASSWORD",nullable = false)
    private String password;

    @Column(name = "ACCOUNT_ROLE",nullable = false)
    private String userRole;

    @Builder
    public Account(String email, String password, String userRole){
        this.email=email;
        this.password=password;
        this.userRole=userRole;
    }
}
