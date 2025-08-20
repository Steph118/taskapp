package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Role;
import com.steph18.demo.repository.RoleRepository;
import com.steph18.demo.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long>
        implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Role t) {
        return t.getId();
    }
}
