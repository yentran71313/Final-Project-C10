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
    public ResponseEntity<?> create(BrandListCreateRequest brandListCreateRequest, MultipartFile multipartFile) throws IOException {
        BrandListResponse brandListResponse = brandService.create(brandListCreateRequest,multipartFile);
        return new ResponseEntity<>(brandListResponse, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(BrandListCreateRequest brandListCreateRequest, MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        Optional<Brand> brandOptional = brandService.findById(id);
        if (brandOptional.isPresent()){
            Brand brand = brandOptional.get();

            BrandListResponse brandListResponse = brandService.update(brandListCreateRequest,multipartFile,brand );
            return new ResponseEntity<>(brandListResponse,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Brand is not exist !!!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable, BrandListRequest request){
        return new ResponseEntity<>(brandService.getAllAndSearch(request,pageable),HttpStatus.OK);
    }
}
