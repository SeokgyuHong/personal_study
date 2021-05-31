package com.example.tutorial.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "test_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class test_user {
    @Id
    @Email
    private String email;

    @Size(min =7)
    private String password;

    @Column(name = "auth")
    private String auth;
}
