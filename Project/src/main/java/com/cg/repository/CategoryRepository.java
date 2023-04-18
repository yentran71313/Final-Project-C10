package com.cg.repository;

import com.cg.model.Category;
import com.cg.service.category.CategoryListRequest;
import com.cg.service.category.CategoryListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("select new com.cg.service.category.CategoryListResponse(c.id,c.name) from Category c where :#{#request.search} is null or c.name  like :#{#request.search}")
    Page<CategoryListResponse> getAllCategory (CategoryListRequest request, Pageable pageable);


    Optional<Category> findCategoriesByName(String name);
}
