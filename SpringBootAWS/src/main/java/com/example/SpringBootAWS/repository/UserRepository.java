package com.example.SpringBootAWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootAWS.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
