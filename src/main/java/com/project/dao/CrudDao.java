package com.project.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CrudDao<E, ID> {
    E create(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    E update(E entity);

    E deleteById(ID id);

    void deleteAllByIds(Set<ID> ids);
}
