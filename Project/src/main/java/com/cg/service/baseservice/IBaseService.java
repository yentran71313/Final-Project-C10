package com.cg.service.baseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<T, S> {
    Page<T> getAllAndSearch(S searchRequest,Pageable pageable);

    
}
