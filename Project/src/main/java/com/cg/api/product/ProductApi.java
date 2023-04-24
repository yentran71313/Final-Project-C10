package com.cg.api.product;


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

import java.io.IOException;

@RestController
@RequestMapping(value = "api/products")
@AllArgsConstructor
public class ProductApi {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<Page<ProductListResponse>> findAll(ProductListRequest request, Pageable pageable){
        return new ResponseEntity<>(productService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> findAll(ProductListRequest request, Pageable pageable){
//        return new ResponseEntity<>(productRepository.getAllProduct( pageable), HttpStatus.OK);
//    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<?> findById(@PathVariable Long idProduct){
        return new ResponseEntity<>(productService.findById(idProduct),HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductListResponse> create(@RequestBody ProductCreateRequest productCreateRequest,MultipartFile[] multipartFiles, BindingResult bindingResult) throws IOException {

         productService.create(productCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{idProduct}")
    public ResponseEntity<ProductListResponse> update(@PathVariable Long idProduct,ProductCreateRequest productCreateRequest, MultipartFile[] multipartFiles,BindingResult bindingResult) throws IOException {
        productCreateRequest.setId(idProduct);
        productService.update(productCreateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
