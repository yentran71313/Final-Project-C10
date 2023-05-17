package com.cg.service.brand;
import com.cg.exception.DataInputException;
import com.cg.model.product.Brand;
import com.cg.repository.BrandRepository;
import com.cg.repository.ImageRepository;
import com.cg.service.baseservice.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BrandService implements IBaseService<BrandListResponse, BrandListRequest, BrandListCreateRequest, BrandListResponse> {

    private final BrandRepository brandRepository;

    private final ImageRepository imageRepository;

    @Override
    public Page<BrandListResponse> getAllAndSearch(BrandListRequest searchRequest, Pageable pageable) {
        return brandRepository.getAllBrand(searchRequest,pageable);
    }

    @Override
    public Optional<BrandListResponse> findById(Long id) {
//        Optional<Brand> brandOptional = brandRepository.findById(id);
//        if (!brandOptional.isPresent()){
//            throw new ResourceNotFoundException(String.format(AppConstant.MESSAGE_NO_EXIST, "Brand"));
//        }
//        return Optional.of(new BrandListResponse(brandOptional.get()));
        return Optional.empty();
    }

    @Override
    public void create(BrandListCreateRequest brandListCreateRequest) {
        brandRepository.save(brandListCreateRequest.toBrand());

    }

    @Override
    public void update(BrandListCreateRequest brandListCreateRequest) {
        Brand brand = brandListCreateRequest.toBrand();
        brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if(!brandOptional.isPresent()){
            throw new DataInputException("Sản phẩm không tồn tại!");
        }
        Brand brand = brandOptional.get();
        brand.setDeleted(true);
        brandRepository.save(brand);
    }
    public void restore(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if(!brandOptional.isPresent()){
            throw new DataInputException("Sản phẩm không tồn tại!");
        }
        Brand brand = brandOptional.get();
        brand.setDeleted(false);
        brandRepository.save(brand);
    }

}
