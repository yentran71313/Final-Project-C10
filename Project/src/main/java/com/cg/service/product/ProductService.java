package com.cg.service.product;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Brand;
import com.cg.model.Category;
import com.cg.model.Image;
import com.cg.model.Product;
import com.cg.repository.BrandRepository;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ImageRepository;
import com.cg.repository.ProductRepository;


import com.cg.service.baseservice.IBaseService;
import com.cg.service.upload.UploadService;
import com.cg.util.AppConstant;
import com.cg.util.UploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class ProductService implements IBaseService<ProductListResponse, ProductListRequest,ProductCreateRequest, ProductDetailResponse> {


    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    @Override
    public Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return productRepository.getAllAndSearch(request, pageable);
    }

    @Override
    public Optional<ProductDetailResponse> findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
        return Optional.of(new ProductDetailResponse(productOptional.get()));
    }



    @Override
    public void create(ProductCreateRequest productCreateRequest) {
        checkExistDb(productCreateRequest);
        Product product = productRepository.save(productCreateRequest.toProduct());
        for (Image image : product.getImages()) {
            image.setProduct(product);
            imageRepository.save(image);
        }
    }





    @Override
    public void update(ProductCreateRequest productCreateRequest)  {
        checkExistDb(productCreateRequest);
        Optional<Product> productOptional = productRepository.findById(productCreateRequest.getId());
        if (!productOptional.isPresent()){
            throw  new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
        Product product = productRepository.save(productCreateRequest.toProduct());
        for (Image image : product.getImages()) {
            image.setProduct(product);
            imageRepository.save(image);
        }
    }

    @Override
    public void delete(Long id) {

    }

    private void checkExistDb(ProductCreateRequest request){
        if(!categoryRepository.existsById(request.getCategoryId())){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Category"));
        };
        if (!brandRepository.existsById(request.getBrandId())){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Brand"));
        }
    }
}
