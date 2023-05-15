package com.cg.service.brand;


import com.cg.model.product.Brand;



import com.cg.model.Image;
import com.cg.repository.BrandRepository;


import com.cg.repository.ImageRepository;
import com.cg.service.baseService.IBaseService;


import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BrandService implements IBaseService<BrandListResponse,BrandListRequest, BrandListCreateRequest, Brand> {

    private final BrandRepository brandRepository;

    private final ImageRepository imageRepository;

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

        Image image = new Image().setId(brandListCreateRequest.getImage());
        imageRepository.save(image);


    }

    @Override
    public void delete(Long id) {

    }

}
