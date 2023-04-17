package com.cg.service.baseService;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

    void deleted();

    void deletedById(Long id);

    boolean existById(Long id);
}
