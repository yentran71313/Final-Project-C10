package com.cg.api.product;


import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.product.Brand;
import com.cg.model.product.Category;
import com.cg.model.Image;
import com.cg.model.product.Product;
import com.cg.repository.BrandRepository;
import com.cg.repository.ImageRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.brand.BrandService;
import com.cg.service.category.CategoryService;
import com.cg.model.product.ProductCreateRequest;

import com.cg.model.product.ProductListRequest;
import com.cg.model.product.ProductListResponse;
import com.cg.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/products")
@AllArgsConstructor
public class ProductApi {
    private final ProductService productService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    private final BrandRepository brandRepository;

    @GetMapping
    public ResponseEntity<Page<ProductListResponse>> findAll(ProductListRequest request, Pageable pageable){
        return new ResponseEntity<>(productService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }



    @GetMapping("/{idProduct}")
    public ResponseEntity<?> findById(@PathVariable Long idProduct){

        Optional<Product> product = productRepository.findById(idProduct);
        if (!product.isPresent()){
            throw  new ResourceNotFoundException("Product not valid");
        }
        ProductListResponse productListResponse = productRepository.findProductResById(idProduct);

        List<Image> images = imageRepository.findByProduct(product.get());
        Map<Long,String> map = new HashMap<>();
        for (Image image: images
        ) {
            map.put(image.getId(),image.getFileUrl());
        }
        productListResponse.setImages(map);
        return new ResponseEntity<>(productListResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductListResponse> create(ProductCreateRequest productCreateRequest,MultipartFile[] multipartFiles, BindingResult bindingResult) throws IOException {
        Optional<Category> categoryOptional = categoryService.findById(productCreateRequest.getCategoryId());
        if(!categoryOptional.isPresent()){
            throw new DataInputException("Category is not exist !");
        }
        Optional<Brand> brandOptional = brandService.findById(productCreateRequest.getBrandId());
        if (!brandOptional.isPresent()){
            throw new DataInputException("Brand is not exist !");
        }
        ProductListResponse productListResponse = productService.create(productCreateRequest,multipartFiles);
        return new ResponseEntity<>(productListResponse,HttpStatus.CREATED);
    }

    @PatchMapping("{idProduct}")
    public ResponseEntity<ProductListResponse> update(@PathVariable Long idProduct,ProductCreateRequest productCreateRequest, MultipartFile[] multipartFiles,BindingResult bindingResult) throws IOException {
        Optional<Category> categoryOptional = categoryService.findById(productCreateRequest.getCategoryId());
        if(!categoryOptional.isPresent()){
            throw new DataInputException("Category is not exist !");
        }
        Optional<Brand> brandOptional = brandService.findById(productCreateRequest.getBrandId());
        if (!brandOptional.isPresent()){
            throw new DataInputException("Brand is not exist !");
        }
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if (!productOptional.isPresent()){
            throw  new ResourceNotFoundException("Product not valid");
        }
        ProductListResponse productListResponse = productService.update(productCreateRequest,multipartFiles,productOptional.get());
        return new ResponseEntity<>(productListResponse,HttpStatus.OK);
    }
}
