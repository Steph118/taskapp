package com.steph18.demo.service;

import java.util.List;

public interface GenericService<T, I> {
    T save(T t);

    void saveAndFlush(T t);

    void saveAll(List<T> tList);

    void saveAllAndFlush(List<T> tList);

    T findById(T t);

    void getReferenceById(T t);

    void delete(T t);

    void deleteById(T t);

    void deleteAll(T t);
}
