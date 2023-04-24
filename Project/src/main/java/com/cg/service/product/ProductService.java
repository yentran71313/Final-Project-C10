package com.cg.service.product;

import com.cg.model.product.Brand;
import com.cg.model.product.Category;
import com.cg.model.Image;
import com.cg.model.product.Product;
import com.cg.model.product.ProductCreateRequest;
import com.cg.model.product.ProductListRequest;
import com.cg.model.product.ProductListResponse;
import com.cg.repository.BrandRepository;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ImageRepository;
import com.cg.repository.ProductRepository;


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
import java.util.*;


@Service
@AllArgsConstructor
@Transactional
public class ProductService implements IBaseService<ProductListResponse, ProductListRequest, ProductCreateRequest, Product> {


    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    private final UploadService uploadService;

    private final ImageRepository imageRepository;

    private final UploadUtil uploadUtil;

    @Override
    public Page<ProductListResponse> getAllAndSearch(ProductListRequest request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return productRepository.getAllAndSearch(request, pageable);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductListResponse create(ProductCreateRequest productCreateRequest, MultipartFile multipartFile) throws IOException {
        return null;
    }

    @Override
    public ProductListResponse create(ProductCreateRequest productCreateRequest, MultipartFile[] multipartFiles) throws IOException {
        ProductListResponse productListResponse = new ProductListResponse();
        if ( multipartFiles==null){
            Category category = new Category().setId(productCreateRequest.getCategoryId());
            Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
            Product product = productCreateRequest.toProduct(category,brand);
            product = productRepository.save(product);

            productListResponse = product.toProductListResponse(null);
        } else {
            Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).get();
            Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
            Product product = productCreateRequest.toProduct(category,brand);
            productRepository.save(product);
            Map<Long,String> images = new HashMap<>();

            for (MultipartFile file : multipartFiles) {
                String str = uploadService.uploadFile(file);
                String fileUrl = str.split("=")[0];
                String cloudId = str.split("=")[1];

                Image image = new Image();
                image.setCloudId(cloudId);
                image.setFileUrl(fileUrl);
                image.setProduct(product);
                image = imageRepository.save(image);

                images.put(image.getId(),image.getFileUrl()); // List = add

            }
            productListResponse = product.toProductListResponse(images);

        }
        return productListResponse;
    }

    @Override
    public Optional<Product> findByName(String name) {
        return null;
    }

    @Override
    public ProductListResponse update(ProductCreateRequest productCreateRequest, MultipartFile multipartFile, Product product) {
        return null;
    }

    @Override
    public ProductListResponse update(ProductCreateRequest productCreateRequest, MultipartFile[] multipartFile, Product product) throws IOException {
        ProductListResponse productListResponse = new ProductListResponse();
        if (multipartFile == null){
            if (product.getImages()==null){
                Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).get();
                Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
                Product productUp = productCreateRequest.toProduct(category,brand);
                productUp.setId(product.getId());
                productUp = productRepository.save(productUp);

                productListResponse = productUp.toProductListResponse(null);
            } else {
                Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).get();
                Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
                Product productUp = productCreateRequest.toProduct(category,brand);
                productUp.setId(product.getId());
                productUp = productRepository.save(productUp);

                List<Image> images = imageRepository.findByProduct(productUp);
                Map<Long,String> map = new HashMap<>();
                for (Image image: images
                ) {
                    map.put(image.getId(),image.getFileUrl());
                }
                productListResponse = productUp.toProductListResponse(map);
            }
        } else {
            if (product.getImages()==null){
                Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).get();
                Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
                Product productUp = productCreateRequest.toProduct(category,brand);
                productUp.setId(product.getId());
                productUp = productRepository.save(productUp);

                Map<Long,String> images = new HashMap<>();

                for (MultipartFile file : multipartFile) {
                    String str = uploadService.uploadFile(file);
                    String fileUrl = str.split("=")[0];
                    String cloudId = str.split("=")[1];

                    Image image = new Image();
                    image.setCloudId(cloudId);
                    image.setFileUrl(fileUrl);
                    image.setProduct(product);
                    image = imageRepository.save(image);

                    images.put(image.getId(),image.getFileUrl());

                }
                productListResponse = productUp.toProductListResponse(images);
            } else {
                Category category = categoryRepository.findById(productCreateRequest.getCategoryId()).get();
                Brand brand = brandRepository.findById(productCreateRequest.getBrandId()).get();
                Product productUp = productCreateRequest.toProduct(category,brand);
                productUp.setId(product.getId());
                productUp = productRepository.save(productUp);
                List<Image> images = imageRepository.findByProduct(productUp);
                for (Image image: images
                ) {
                    uploadService.destroyFile(image.getCloudId(),uploadUtil.buildImageDestroyParams(image,image.getCloudId()));
                    imageRepository.delete(image);
                }
                Map<Long,String> map = new HashMap<>();

                for (MultipartFile file : multipartFile) {
                    String str = uploadService.uploadFile(file);
                    String fileUrl = str.split("=")[0];
                    String cloudId = str.split("=")[1];

                    Image image = new Image();
                    image.setCloudId(cloudId);
                    image.setFileUrl(fileUrl);
                    image.setProduct(product);
                    image = imageRepository.save(image);

                    map.put(image.getId(),image.getFileUrl());
                }
                productListResponse = productUp.toProductListResponse(map);
            }
        }

        return productListResponse;
    }
}
