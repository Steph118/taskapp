package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Permission;
import com.steph18.demo.repository.PermissionRepository;
import com.steph18.demo.service.PermissionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long>
        implements PermissionService {

    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Permission, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Permission t) {
        return t.getId();
    }
}
