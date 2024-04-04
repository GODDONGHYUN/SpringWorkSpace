package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.board.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
		boolean existsByTelNumber(String telNumber);
		boolean existsByNickname(String nickname);
		boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

}
