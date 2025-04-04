package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Person;
import com.steph18.demo.repository.PersonRepository;
import com.steph18.demo.service.PersonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long>
        implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Person, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Person p) {
        return p.getId();
    }
}
