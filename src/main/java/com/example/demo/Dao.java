package com.example.demo;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> Handle basic methods for achieving, finding a specific, delete, show data object
 */
public interface Dao<T> {

    T findById(long id);

    List<T> getAll();

    void add(T t);


    Boolean delete(T t);
}