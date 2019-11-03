package com.project.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CrudDao<E, ID> {
    boolean save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    boolean update(E entity);

    default void deleteById(ID id){
        throw new UnsupportedOperationException("The use of this method is not allowed");
    }

    default void deleteAllByIds(Set<ID> ids){
        throw new UnsupportedOperationException("The use of this method is not allowed");
    }
}
