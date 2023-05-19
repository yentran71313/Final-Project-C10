package com.cg.repository;

import com.cg.model.product.Product;
import com.cg.model.product.ProductListRequest;
import com.cg.model.product.ProductListResponse;
import com.cg.service.product.ProductDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    @Query("select new com.cg.model.product.ProductListResponse(p.id, p.name, p.price,p.marketPrice,p.category.name,p.brand.name,p.warranty)  from Product  p " +
//            "where (:#{#request.search} is null or p.name  like :#{#request.search} or :#{#request.search}  like p.description)  " +
//            "and (:#{#request.brandId} is null or :#{#request.brandId} = p.brand.id) " +
//            "and (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)")
//    Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable);

    @Query("select p from Product p " +
            "where (:#{#request.search} is null or p.name  like :#{#request.search} or :#{#request.search}  like p.description)  " +
            "and (:#{#request.brandId} is null or :#{#request.brandId} = p.brand.id) " +
            "and (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)" +
            "and (p.price between :#{#request.minPrice} and :#{#request.maxPrice}) "
    )
    Page<Product> getAllAndSearch(Pageable pageable,ProductListRequest request);

    @Query("select p  from Product  p  " +
            "where (:#{#request.search} is null or p.name  like :#{#request.search} or :#{#request.search}  like p.description)  " +
            "and (:#{#request.brandId} is null or :#{#request.brandId} = p.brand.id) " +
            "and (:#{#request.categoryId} is null or :#{#request.categoryId} = p.category.id)")
    Page<Product> findAllProduct(ProductListRequest request,Pageable pageable);
    @Query("select p from Product p  where p.id = :id ")
    Optional<Product> findProductById(Long id);

}
