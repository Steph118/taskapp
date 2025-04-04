package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Task;
import com.steph18.demo.repository.TaskRepository;
import com.steph18.demo.service.TaskService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Long>
        implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Task, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Task t) {
        return t.getId();
    }
}
