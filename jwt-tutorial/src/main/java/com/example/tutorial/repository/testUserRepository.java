package com.example.tutorial.repository;

import com.example.tutorial.entity.test_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface testUserRepository extends JpaRepository<test_user,Integer> {
        Optional<test_user> findByEmail(String email);
}
