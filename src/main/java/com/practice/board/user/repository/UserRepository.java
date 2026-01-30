package com.practice.board.user.repository;

import com.practice.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository; // Repository 대신 JpaRepository
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}