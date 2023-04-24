package com.cg.service.brand;

import com.cg.model.product.Brand;
import com.cg.model.Image;
import com.cg.repository.BrandRepository;
import com.cg.repository.ImageRepository;

import com.cg.service.baseservice.IBaseService;
import com.cg.service.upload.UploadService;
import com.cg.util.UploadUtil;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BrandService implements IBaseService<BrandListResponse,BrandListRequest, BrandListCreateRequest, Brand> {

    private final UploadService uploadService;

    private final BrandRepository brandRepository;

    private final ImageRepository imageRepository;

    private final UploadUtil uploadUtil;


    @Override
    public Page<BrandListResponse> getAllAndSearch(BrandListRequest searchRequest, Pageable pageable) {
        return brandRepository.getAllBrand(searchRequest,pageable);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }


    @Override
    public BrandListResponse create(BrandListCreateRequest brandListCreateRequest, MultipartFile multipartFile) throws IOException {
        Brand brand = new Brand();
        Image image = new Image();
        if (multipartFile !=null){
            String str = uploadService.uploadFile(multipartFile);
            String fileUrl = str.split("=")[0];
            String cloudId = str.split("=")[1];

            brand.setName(brandListCreateRequest.getName());
            brand = brandRepository.save(brand);

            image.setCloudId(cloudId);
            image.setFileUrl(fileUrl);
            image.setBrand(brand);
            imageRepository.save(image);


            brand.setImage(image);
            BrandListResponse brandListResponse = brand.toBrandListResponse();
            return brandListResponse;
        } else {
            brand.setName(brandListCreateRequest.getName());
            brand.setImage(null);
            brand = brandRepository.save(brand);
            BrandListResponse brandListResponse = new BrandListResponse();
            brandListResponse.setId(brand.getId());
            brandListResponse.setName(brand.getName());
            return brandListResponse;
        }
    }

    @Override
    public BrandListResponse create(BrandListCreateRequest brandListCreateRequest, MultipartFile[] multipartFile) throws IOException {
        return null;
    }


    @Override
    public Optional<Brand> findByName(String name) {
        return null;
    }

    @Override
    public BrandListResponse update(BrandListCreateRequest brandListCreateRequest, MultipartFile multipartFile,Brand brand) throws IOException {
        BrandListResponse brandListResponse = new BrandListResponse();
        if (multipartFile==null){
            Image image = brand.getImage();
            if (image==null){
                brand.setName(brandListCreateRequest.getName());
                brand = brandRepository.save(brand);
                brandListResponse.setId(brand.getId());
                brandListResponse.setName(brand.getName());
                brandListResponse.setFileUrl(null);
            } else {
                brand.setName(brandListCreateRequest.getName());
                brandListResponse = brandRepository.save(brand).toBrandListResponse();
                imageRepository.delete(image);
                brandListResponse.setFileUrl(null);
            }


        } else {
            Image currentImage = brand.getImage();
            if (currentImage ==null){
                String str = uploadService.uploadFile(multipartFile);
                String fileUrl = str.split("=")[0];
                String cloudId = str.split("=")[1];
                brand.setName(brandListCreateRequest.getName());
                brand = brandRepository.save(brand);

                Image image = new Image();
                image.setCloudId(cloudId);
                image.setFileUrl(fileUrl);
                image.setBrand(brand);
                imageRepository.save(image);

                brandListResponse.setName(brand.getName());
                brandListResponse.setId(brand.getId());
                brandListResponse.setFileUrl(image.getFileUrl());
            } else {
                uploadService.destroyFile(currentImage.getCloudId(),uploadUtil.buildImageDestroyParams(currentImage,currentImage.getCloudId()));
                String str = uploadService.uploadFile(multipartFile);
                String fileUrl = str.split("=")[0];
                String cloudId = str.split("=")[1];
                brand.setName(brandListCreateRequest.getName());
                brand = brandRepository.save(brand);

                currentImage.setCloudId(cloudId);
                currentImage.setFileUrl(fileUrl);
                currentImage.setBrand(brand);
                imageRepository.save(currentImage);

                brandListResponse.setName(brand.getName());
                brandListResponse.setId(brand.getId());
                brandListResponse.setFileUrl(currentImage.getFileUrl());
            }
        }



        return brandListResponse;
    }

    @Override
    public BrandListResponse update(BrandListCreateRequest brandListCreateRequest, MultipartFile[] multipartFile, Brand brand) throws IOException {
        return null;
    }
}
