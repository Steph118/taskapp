package com.steph18.demo.repository;

import com.steph18.demo.entities.Person;
import com.steph18.demo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    
}
