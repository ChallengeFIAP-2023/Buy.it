package br.com.fiap.domain.resources;

import jakarta.ws.rs.core.Response;

public interface Resource<T, U> {

    Response findAll();

    Response findById(U id);

    Response persist(T t);

    Response update(T t);

    Response delete(T t);
}