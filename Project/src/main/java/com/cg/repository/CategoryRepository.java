package com.cg.repository;

import com.cg.model.product.Category;
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

    @Query("select new com.cg.service.category.CategoryListResponse(c.id,c.name,c.image.fileUrl,c.image.id) " +
            "from Category c left join Image img on img.id = c.image.id " +
            "where :#{#request.search} is null or c.name  like :#{#request.search}  and c.deleted=:#{#request.deleted} ")
    Page<CategoryListResponse> getAllCategory (CategoryListRequest request, Pageable pageable);


    Optional<Category> findCategoriesByName(String name);
}
