package com.steph18.demo.serviceimpl;

import com.steph18.demo.service.GenericService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T, I>
        implements GenericService<T, I> {

    protected ModelMapper modelMapper = new ModelMapper();

    public abstract JpaRepository<T, I> getRepository();

    public abstract I getId(T t);

    public T save(T t) {
        return this.getRepository().save(t);
    }

    public void saveAndFlush(T t) {
        this.getRepository().saveAndFlush(t);
    }

    public void saveAll(List<T> tList) {
        this.getRepository().saveAll(tList);
    }

    public void saveAllAndFlush(List<T> tList) {
        getRepository().saveAllAndFlush(tList);
    }

    public T findById(I i) {
        Optional<T> opt = this.getRepository().findById(i);
        return opt.orElseThrow();
    }

    public void getReferenceById(T t) {
        this.getRepository().getReferenceById(getId(t));
    }

    public void delete(T t) {
        this.getRepository().delete(t);
    }

    public void deleteById(T t) {
        this.getRepository().deleteById(getId(t));
    }

    public void deleteAll(T t) {
        this.getRepository().deleteAll();
    }

    public boolean existsById(T t) {
        return this.getRepository().existsById(getId(t));
    }

    public T update(T t) {
        return (this.existsById(t)) ? this.save(t) : null;
    }

    public List<T> findAll() {
        return this.getRepository().findAll();
    }
}
