package com.cg.service.baseservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
public interface IBaseService<T, S, R, Z> {
    Page<T> getAllAndSearch(S searchRequest,Pageable pageable);

    Optional<Z> findById(Long id);

    void create(R r);
    void update(R r);

    void delete(Long id);
}


