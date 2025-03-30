package com.steph18.demo.repository;

import com.steph18.demo.entities.Task;
import com.steph18.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
}
