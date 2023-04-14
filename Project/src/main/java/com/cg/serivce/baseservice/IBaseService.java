package com.cg.serivce.baseservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<T, S> {
    Page<T> getAllAndSearch(S searchRequest,Pageable pageable);

    
}
