package com.cg.service.category;

import com.cg.model.Category;
import com.cg.repository.CategoryRepository;
import com.cg.service.baseService.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements IBaseService<CategoryListResponse, CategoryListRequest,CategoryListCreateRequest,Category> {

    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryListResponse> getAllAndSearch(CategoryListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return categoryRepository.getAllCategory(request,pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public CategoryListResponse create(CategoryListCreateRequest categoryListCreateRequest, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public CategoryListResponse create(CategoryListCreateRequest categoryListCreateRequest, MultipartFile[] multipartFile) throws IOException {
        return null;
    }


    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findCategoriesByName(name);
    }

    @Override
    public CategoryListResponse update(CategoryListCreateRequest categoryListCreateRequest, MultipartFile multipartFile, Category category) {
        return null;
    }

    @Override
    public CategoryListResponse update(CategoryListCreateRequest categoryListCreateRequest, MultipartFile[] multipartFile, Category category) throws IOException {
        return null;
    }
}
