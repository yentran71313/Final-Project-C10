package com.cg.api.category;


import com.cg.model.product.Category;
import com.cg.service.category.CategoryListCreateRequest;
import com.cg.service.category.CategoryListRequest;
import com.cg.service.category.CategoryListResponse;
import com.cg.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/categories")
@AllArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<Page<CategoryListResponse>> findAll (CategoryListRequest request, Pageable pageable){
        return new ResponseEntity<>(categoryService.getAllAndSearch(request,pageable), HttpStatus.OK);
    }

    @GetMapping("/{idCategory}")
    private ResponseEntity<?> findById(@PathVariable Long idCategory){
        return new ResponseEntity<>(categoryService.findById(idCategory).get(),HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody CategoryListCreateRequest categoryListCreateRequest, BindingResult bindingResult){
        new CategoryListCreateRequest().validate(categoryListCreateRequest,bindingResult);
        categoryService.create(categoryListCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping ("/{idCategory}")
    private ResponseEntity<?> update(@PathVariable Long idCategory,@RequestBody CategoryListCreateRequest categoryListCreateRequest, BindingResult bindingResult){


        categoryListCreateRequest.setId(idCategory);

        categoryService.update(categoryListCreateRequest);
        return new  ResponseEntity<>(HttpStatus.OK);
    }





}
