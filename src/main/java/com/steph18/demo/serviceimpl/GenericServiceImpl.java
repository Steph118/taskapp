package com.steph18.demo.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T, I> {

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

    public T findById(T t) {
        Optional<T> opt = this.getRepository().findById(this.getId(t));
        return opt.orElse(null);
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
}
