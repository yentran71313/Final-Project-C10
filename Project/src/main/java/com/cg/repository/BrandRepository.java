package com.cg.repository;

import com.cg.model.product.Brand;
import com.cg.service.brand.BrandListRequest;
import com.cg.service.brand.BrandListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("select new com.cg.service.brand.BrandListResponse(b.id,b.name,b.image.fileUrl) " +
            "from Brand b left join Image img on img.id = b.image.id " +
            "where :#{#request.search} is null or b.name  like :#{#request.search}")
    Page<BrandListResponse> getAllBrand(BrandListRequest request, Pageable pageable);


}
