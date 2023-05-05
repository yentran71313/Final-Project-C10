package com.cg.service.brand;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Brand;
import com.cg.model.Image;
import com.cg.model.Product;
import com.cg.repository.BrandRepository;


import com.cg.service.baseservice.IBaseService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BrandService implements IBaseService<BrandListResponse,BrandListRequest, BrandListCreateRequest, Brand> {

    private final BrandRepository brandRepository;

    @Override
    public Page<BrandListResponse> getAllAndSearch(BrandListRequest searchRequest, Pageable pageable) {
        return brandRepository.getAllBrand(searchRequest,pageable);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void create(BrandListCreateRequest brandListCreateRequest) {
        brandRepository.save(brandListCreateRequest.toBrand());

    }

    @Override
    public void update(BrandListCreateRequest brandListCreateRequest) {
        Brand brand = brandListCreateRequest.toBrand();
        brandRepository.save(brand);

    }

    @Override
    public void delete(Long id) {

    }

}
