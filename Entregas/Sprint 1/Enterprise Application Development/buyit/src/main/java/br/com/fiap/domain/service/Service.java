package br.com.fiap.domain.service;

import java.util.List;

public interface Service<T, U> {

    List<T> findAll();

    T findById(U id);

    T persist(T t);

    T update(T t);

    boolean delete(T t);
}