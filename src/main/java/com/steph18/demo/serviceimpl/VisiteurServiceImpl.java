package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Visiteur;
import com.steph18.demo.repository.VisiteurRepository;
import com.steph18.demo.service.VisiteurService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class VisiteurServiceImpl extends GenericServiceImpl<Visiteur, Long>
        implements VisiteurService {

    private final VisiteurRepository repository;

    public VisiteurServiceImpl(VisiteurRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Visiteur, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Visiteur p) {
        return p.getId();
    }
}
