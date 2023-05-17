package com.cg.service.category;


import com.cg.model.product.Category;
import com.cg.repository.CategoryRepository;


import com.cg.service.baseservice.IBaseService;
import com.cg.service.product.ProductDetailResponse;

import com.cg.exception.ResourceNotFoundException;

import com.cg.service.baseservice.IBaseService;
import com.cg.util.AppConstant;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements IBaseService<CategoryListResponse, CategoryListRequest,CategoryListCreateRequest,CategoryListResponse> {

    private final CategoryRepository categoryRepository;
    @Override
    public Page<CategoryListResponse> getAllAndSearch(CategoryListRequest searchRequest, Pageable pageable) {
        if(searchRequest.getSearch() != null){
            searchRequest.setSearch("%" + searchRequest.getSearch() + "%");
        }
        return categoryRepository.getAllCategory(searchRequest, pageable);
    }

    @Override
    public Optional<CategoryListResponse> findById(Long id) {

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()){
            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Category"));
        }
        return Optional.of(new CategoryListResponse(categoryOptional.get()));
    }

    @Override
    public void create(CategoryListCreateRequest categoryListCreateRequest) {
        Category category = categoryRepository.save(categoryListCreateRequest.toCategory());
        categoryRepository.save(category);
    }

    @Override
    public void update(CategoryListCreateRequest categoryListCreateRequest) {
        Category category = categoryListCreateRequest.toCategory();
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findByName(String name) {
        return null;
    }
}
