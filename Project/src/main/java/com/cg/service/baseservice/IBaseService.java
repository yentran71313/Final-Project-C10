package com.cg.service.baseService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IBaseService<T, S, R, Z> {
    Page<T> getAllAndSearch(S searchRequest,Pageable pageable);

    Optional<Z> findById(Long id);

    T create(R r, MultipartFile multipartFile) throws IOException;

    T create(R r, MultipartFile[] multipartFile) throws IOException;

    Optional<Z> findByName(String name);

    T update(R r, MultipartFile multipartFile, Z z) throws IOException;

    T update(R r, MultipartFile multipartFile[], Z z) throws IOException;
}


