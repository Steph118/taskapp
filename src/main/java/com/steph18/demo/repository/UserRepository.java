package com.steph18.demo.repository;

import com.steph18.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> queryByUsername(String username);

    boolean existsByUsername(String username);
}
