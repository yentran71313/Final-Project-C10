package com.cg.service.category;

import com.cg.model.Category;
import com.cg.repository.CategoryRepository;

import com.cg.service.baseservice.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements IBaseService<CategoryListResponse, CategoryListRequest,Category> {

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
    public CategoryListResponse create(Category category) {
        return categoryRepository.save(category).toCategoryListResponse();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findCategoriesByName(name);
    }
}
