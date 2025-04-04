package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.CategoryTask;
import com.steph18.demo.repository.CategoryTaskRepository;
import com.steph18.demo.service.CategoryTaskService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryTaskServiceImpl extends GenericServiceImpl<CategoryTask, Long>
        implements CategoryTaskService {

    private final CategoryTaskRepository repository;

    public CategoryTaskServiceImpl(CategoryTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<CategoryTask, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(CategoryTask t) {
        return t.getId();
    }

}
