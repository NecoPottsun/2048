package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    T findById(long id);

    List<T> getAll();

    void add(T t);


    Boolean delete(T t);
}