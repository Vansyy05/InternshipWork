package com.onerivet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onerivet.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	 Optional<UserEntity> findByusername(String username);

}
