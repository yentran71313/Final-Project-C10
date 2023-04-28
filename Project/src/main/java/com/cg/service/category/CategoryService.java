package com.cg.service.category;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
import com.cg.model.Image;
import com.cg.model.Product;
import com.cg.repository.CategoryRepository;
import com.cg.service.baseService.IBaseService;
import com.cg.service.product.ProductDetailResponse;
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

    }

    public Optional<Category> findByName(String name) {
        return null;
    }

//    private final CategoryRepository categoryRepository;
//
//    @Override
//    public Page<CategoryListResponse> getAllAndSearch(CategoryListRequest request, Pageable pageable) {
//        if(request.getSearch() != null){
//            request.setSearch("%" + request.getSearch() + "%");
//        }
//        return categoryRepository.getAllCategory(request,pageable);
//    }
//
//    @Override
//    public Optional<Category> findById(Long id) {
//        return categoryRepository.findById(id);
//    }
//
//    @Override
//    public CategoryListResponse create(CategoryListCreateRequest categoryListCreateRequest, MultipartFile multipartFile) throws IOException {
//        return null;
//    }
//
//    @Override
//    public CategoryListResponse create(CategoryListCreateRequest categoryListCreateRequest, MultipartFile[] multipartFile) throws IOException {
//        return null;
//    }
//
//
//    public Optional<Category> findByName(String name) {
//        return categoryRepository.findCategoriesByName(name);
//    }
//
//    @Override
//    public CategoryListResponse update(CategoryListCreateRequest categoryListCreateRequest, MultipartFile multipartFile, Category category) {
//        return null;
//    }
//
//    @Override
//    public CategoryListResponse update(CategoryListCreateRequest categoryListCreateRequest, MultipartFile[] multipartFile, Category category) throws IOException {
//        return null;
//    }
}
