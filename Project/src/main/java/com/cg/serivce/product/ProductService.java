package com.cg.serivce.product;

import com.cg.repository.ProductRepository;
import com.cg.serivce.baseservice.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProductService implements IBaseService<ProductListResponse, ProductListRequest> {


    private final ProductRepository productRepository;

    @Override
    public Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return productRepository.getAllAndSearch(request, pageable);
    }
}
