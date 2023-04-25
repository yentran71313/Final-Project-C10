package com.cg.api.brand;

import com.cg.model.product.Brand;
import com.cg.service.brand.BrandListCreateRequest;
import com.cg.service.brand.BrandListRequest;
import com.cg.service.brand.BrandListResponse;
import com.cg.service.brand.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/brands")
@AllArgsConstructor
public class BrandApi {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BrandListCreateRequest brandListCreateRequest) throws IOException {
        brandService.create(brandListCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody BrandListCreateRequest brandListCreateRequest,  @PathVariable Long id)  {
        brandListCreateRequest.setId(id);
        brandService.update(brandListCreateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable, BrandListRequest request){
        return new ResponseEntity<>(brandService.getAllAndSearch(request,pageable),HttpStatus.OK);
    }
}
