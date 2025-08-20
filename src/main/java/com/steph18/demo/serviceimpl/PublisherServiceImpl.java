package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Publisher;
import com.steph18.demo.repository.PublisherRepository;
import com.steph18.demo.service.PublisherService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl extends GenericServiceImpl<Publisher, Long>
        implements PublisherService {

    private final PublisherRepository repository;

    public PublisherServiceImpl(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Publisher, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Publisher p) {
        return p.getId();
    }
}
