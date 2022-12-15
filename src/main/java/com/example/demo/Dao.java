package com.example.demo;

import java.util.List;
import java.util.Optional;

/**
 * Handles basic methods for achieving, finding a specific, delete, show data object
 * @param <T> is the Class implemented type
    @author Thanh Mai Duyen Trang
 */
public interface Dao<T> {

    /**
     * Find Type T by an id
     * @param id the id of type T
     * @return the object T
     */
    T findById(long id);

    /**
     * Gets the list of objects T
     * @return list of objects T
     */
    List<T> getAll();

    /**
     * Adds a new T's object
     * @param t the object T that wanted to add
     */
    void add(T t);

    /**
     * Deletes an object T
     * @param t the object T that wanted to delete
     * @return <code>false</code> if delete unsuccessful;
     *          <code>true</code> if delete successful
     */
    Boolean delete(T t);
}