package com.cg.service.baseservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBaseService<T, S, R> {
    Page<T> getAllAndSearch(S searchRequest,Pageable pageable);

    Optional<R> findById(Long id);

    T create(R r);

    Optional<R> findByName(String name);
}
