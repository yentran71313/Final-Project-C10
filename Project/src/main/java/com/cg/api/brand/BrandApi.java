package com.cg.api.brand;

import com.cg.exception.DataInputException;
import com.cg.service.brand.BrandListCreateRequest;
import com.cg.service.brand.BrandListRequest;
import com.cg.service.brand.BrandListResponse;
import com.cg.service.brand.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/brands")
@AllArgsConstructor
public class BrandApi {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable, BrandListRequest request){
        return new ResponseEntity<>(brandService.getAllAndSearch(request,pageable),HttpStatus.OK);
    }
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

    @PatchMapping("/delete/{brandId}")
    public ResponseEntity<?> delete(@PathVariable Long brandId) throws IOException {
        try {
            brandService.delete(brandId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataInputException("Lỗi không thể xóa sản phẩm!");
        }
    }
    @PatchMapping("/restore/{brandId}")
    public ResponseEntity<?> restore(@PathVariable Long brandId) throws IOException {
        try {
            brandService.restore(brandId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataInputException("Lỗi không thể khôi phục sản phẩm!");
        }
    }
}
