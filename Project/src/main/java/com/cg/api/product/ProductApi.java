package com.cg.api.product;


import com.cg.model.product.ProductCreateRequest;
import com.cg.model.product.ProductListRequest;
import com.cg.model.product.ProductListResponse;

import com.cg.service.product.ProductService;

import com.cg.utils.AppUtils;
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

    private final AppUtils appUtil;


    @GetMapping
    public ResponseEntity<Page<ProductListResponse>> findAll(ProductListRequest request, Pageable pageable){
        return new ResponseEntity<>(productService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }



    @GetMapping("/{idProduct}")
    public ResponseEntity<?> findById(@PathVariable Long idProduct){
        
        return new ResponseEntity<>(productService.findById(idProduct),HttpStatus.OK);

    }

    @PostMapping

    public ResponseEntity<?> create(@RequestBody ProductCreateRequest productCreateRequest, BindingResult bindingResult) throws IOException {
        new ProductCreateRequest().validate(productCreateRequest,bindingResult);
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }
         productService.create(productCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{idProduct}")
    public ResponseEntity<?> update(@PathVariable Long idProduct,@RequestBody ProductCreateRequest productCreateRequest,BindingResult bindingResult) throws IOException {
        new ProductCreateRequest().validate(productCreateRequest,bindingResult);
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult);
        }
        productCreateRequest.setId(idProduct);
        productService.update(productCreateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
