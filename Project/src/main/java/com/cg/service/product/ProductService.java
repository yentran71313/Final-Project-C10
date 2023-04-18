package com.cg.service.product;

import com.cg.model.Product;
import com.cg.repository.ProductRepository;


import com.cg.service.baseservice.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService implements IBaseService<ProductListResponse, ProductListRequest, Product> {


    private final ProductRepository productRepository;

    @Override
    public Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return productRepository.getAllAndSearch(request, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }


    @Override
    public ProductListResponse create(Product product) {
        return null;
    }

    @Override
    public Optional<Product> findByName(String name) {
        return null;
    }


}
