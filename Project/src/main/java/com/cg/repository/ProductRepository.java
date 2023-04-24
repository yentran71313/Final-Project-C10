package com.cg.repository;

import com.cg.model.Product;
import com.cg.service.product.ProductListRequest;
import com.cg.service.product.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select new com.cg.service.product.ProductListResponse(p.id, p.name, p.price,p.marketPrice,p.category.name,p.brand.name,p.warranty)  from Product  p left join Image img on p.id = img.product.id " +
            "where (:#{#request.search} is null or p.name  like :#{#request.search} or :#{#request.search}  like p.description)  " +
            "and (:#{#request.brandId} is null or :#{#request.brandId} = p.brand.id) " +
            "and (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)")
    Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable);


    @Query("select new com.cg.service.product.ProductListResponse(p.id,p.name,p.price,p.marketPrice,p.category.name,p.brand.name,p.warranty) from Product p  where p.id = :id")
    ProductListResponse findProductResById(Long id);
}
