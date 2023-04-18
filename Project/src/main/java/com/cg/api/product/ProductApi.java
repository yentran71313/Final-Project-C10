package com.cg.api.product;


import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.service.category.CategoryService;
import com.cg.service.product.ProductCreateRequest;

import com.cg.service.product.ProductListRequest;
import com.cg.service.product.ProductListResponse;
import com.cg.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/products")
@AllArgsConstructor
public class ProductApi {
    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<ProductListResponse>> findAll(ProductListRequest request, Pageable pageable){
        return new ResponseEntity<>(productService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductListResponse> findById(@PathVariable Long idProduct){
        Optional<Product> productListResponse = productService.findById(idProduct);
        if (productListResponse.isPresent()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductListResponse> create(ProductCreateRequest productCreateRequest, BindingResult bindingResult){
        Optional<Category> categoryOptional = categoryService.findById(productCreateRequest.getCategoryId());
        if(!categoryOptional.isPresent()){
            throw new DataInputException("Category is not exist !");
        }

        MultipartFile multipartFile = productCreateRequest.getMultipartFile();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<ProductListResponse> update(){
        return null;
    }
}
