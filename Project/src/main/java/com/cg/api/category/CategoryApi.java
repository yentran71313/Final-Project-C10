package com.cg.api.category;


import com.cg.model.Category;
import com.cg.service.category.CategoryListCreateRequest;
import com.cg.service.category.CategoryListRequest;
import com.cg.service.category.CategoryListResponse;
import com.cg.service.category.CategoryService;
import com.cg.util.AppUtil;
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

    private final AppUtil appUtil;

    @GetMapping
    public ResponseEntity<Page<CategoryListResponse>> findAll (CategoryListRequest request, Pageable pageable){
        return new ResponseEntity<>(categoryService.getAllAndSearch(request,pageable), HttpStatus.OK);
    }

    @GetMapping("/{idCategory}")
    private ResponseEntity<?> findById(@PathVariable Long idCategory){
        return new ResponseEntity<>(categoryService.findById(idCategory).get().toCategoryListResponse(),HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody CategoryListCreateRequest categoryListCreateRequest, BindingResult bindingResult){
        new CategoryListCreateRequest().validate(categoryListCreateRequest,bindingResult);
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult) ;
        }
        if (categoryService.findByName(categoryListCreateRequest.getName()).isPresent()){
            return new ResponseEntity<>("Name have existed",HttpStatus.BAD_REQUEST);
        }

        Category category = categoryListCreateRequest.toCategory();
        return new ResponseEntity<>(categoryService.create(category),HttpStatus.CREATED);
    }

    @PatchMapping ("/{idCategory}")
    private ResponseEntity<?> update(@PathVariable Long idCategory,@RequestBody CategoryListCreateRequest categoryListCreateRequest, BindingResult bindingResult){

        new CategoryListCreateRequest().validate(categoryListCreateRequest,bindingResult);
        if (bindingResult.hasErrors()){
            return appUtil.mapErrorToResponse(bindingResult) ;
        }
        if (categoryService.findByName(categoryListCreateRequest.getName()).isPresent()){
            return new ResponseEntity<>("Name have existed",HttpStatus.BAD_REQUEST);
        }
        Optional<Category> category = categoryService.findById(idCategory);
        if (category.isPresent()){
            return new ResponseEntity<>(categoryService.create(category.get().setName(categoryListCreateRequest.getName())),HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category is not exist !!!", HttpStatus.NOT_FOUND);
        }
    }


}
