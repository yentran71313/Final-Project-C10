package com.cg.service.product;


import com.cg.model.product.Brand;
import com.cg.model.product.Category;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;

import com.cg.model.Image;
import com.cg.model.product.Product;
import com.cg.model.product.ProductCreateRequest;
import com.cg.model.product.ProductListRequest;
import com.cg.model.product.ProductListResponse;
import com.cg.repository.BrandRepository;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ImageRepository;
import com.cg.repository.ProductRepository;



import com.cg.service.upload.UploadService;
import com.cg.util.AppConstant;
import com.cg.util.UploadUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class ProductService implements com.cg.service.baseservice.IBaseService<ProductListResponse, ProductListRequest, ProductCreateRequest, Product> {




    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    @Override
    public Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        Page<Product> products = productRepository.getAllAndSearch(pageable,request);

        List<ProductListResponse> productListResponses = products.stream().map(ProductListResponse::new).collect(Collectors.toList());
        Page<ProductListResponse> page = new PageImpl<>(productListResponses, pageable, products.getTotalElements());
        return page;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
        return productRepository.findById(id);
    }

    public ProductDetailResponse findProductDetailById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Product"));
        }
        ProductDetailResponse productDetailResponse = new ProductDetailResponse(productOptional.get());
        return productDetailResponse;
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
