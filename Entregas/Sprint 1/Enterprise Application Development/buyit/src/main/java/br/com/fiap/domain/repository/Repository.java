package br.com.fiap.domain.repository;

import java.util.List;

public interface Repository<T, U> {

    List<T> findAll();

    T findById(U id);

    T persist(T t);

    T update(T t);

    boolean delete(T t);

}