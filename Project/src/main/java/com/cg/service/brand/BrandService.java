package com.cg.service.brand;

import com.cg.model.Brand;
import com.cg.service.baseservice.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BrandService implements IBaseService<BrandListResponse, BrandListCreateRequest, Brand> {
    @Override
    public Page<BrandListResponse> getAllAndSearch(BrandListCreateRequest searchRequest, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public BrandListResponse create(Brand brand) {
        return null;
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return null;
    }
}
