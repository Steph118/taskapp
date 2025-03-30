package com.steph18.demo.repository;

import com.steph18.demo.entities.CategoryTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryTaskRepository extends JpaRepository<CategoryTask,Long> {
    
}
