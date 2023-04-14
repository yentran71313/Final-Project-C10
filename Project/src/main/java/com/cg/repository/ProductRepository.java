package com.cg.repository;

import com.cg.model.Product;
import com.cg.serivce.product.ProductListRequest;
import com.cg.serivce.product.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select new com.cg.serivce.product.ProductListResponse(p.id, p.name, p.price)  from Product  p " +
            "where (:#{#request.search} is null or p.name  like :#{#request.search} or :#{#request.search}  like p.description)  " +
            "and (:#{#request.brandId} is null or :#{#request.brandId} = p.brand.id) " +
            "and (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)")
    Page<ProductListResponse> getAllAndSearch(@NotNull ProductListRequest request, Pageable pageable);
}
