package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Task;
import com.steph18.demo.entities.User;
import com.steph18.demo.repository.TaskRepository;
import com.steph18.demo.repository.UserRepository;
import com.steph18.demo.service.TaskService;
import com.steph18.demo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,Long>
        implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<User, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(User t) {
        return t.getId();
    }
}
