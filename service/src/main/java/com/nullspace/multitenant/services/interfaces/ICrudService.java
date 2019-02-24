package com.nullspace.multitenant.services.interfaces;

import com.nullspace.multitenant.services.exceptions.AlreadyExists;

import java.util.List;

public interface ICrudService<T, ID>  {
    List<T> findAll();

    T findById(ID id);

    T save(T object) throws AlreadyExists;

    void delete(T object);

    void deleteById(ID id);
}
