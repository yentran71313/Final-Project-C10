package com.cg.service.customer;

import com.cg.model.Image;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import com.cg.model.dto.customerDTO.CustomerCreateDTO;
import com.cg.model.dto.customerDTO.CustomerRequestDTO;
import com.cg.model.dto.customerDTO.CustomerResDTO;
import com.cg.repository.CustomerRepository;
import com.cg.repository.ImageRepository;
import com.cg.repository.LocationRegionRepository;
import com.cg.service.baseservice.IBaseService;
import com.cg.service.upload.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements IBaseService<CustomerResDTO, CustomerRequestDTO, CustomerCreateDTO, Customer> {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRegionRepository locationRegionRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Page<CustomerResDTO> getAllAndSearch(CustomerRequestDTO searchRequest, Pageable pageable) {
        return customerRepository.getAllAndSearch(searchRequest, pageable);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public CustomerResDTO create(CustomerCreateDTO customerCreateDTO, MultipartFile multipartFile) throws IOException {
        CustomerResDTO customerResDTO = new CustomerResDTO();
        LocationRegion locationRegion = new LocationRegion();
        locationRegion.setProvinceId(customerCreateDTO.getProvinceId());
        locationRegion.setProvinceName(customerCreateDTO.getProvinceName());
        locationRegion.setDistrictId(customerCreateDTO.getDistrictId());
        locationRegion.setDistrictName(customerCreateDTO.getDistrictName());
        locationRegion.setWardId(customerCreateDTO.getWardId());
        locationRegion.setWardName(customerCreateDTO.getWardName());
        locationRegionRepository.save(locationRegion);

        if (multipartFile == null) {
            Customer customer = customerCreateDTO.toCustomer(locationRegion);
            customerRepository.save(customer);

            customerResDTO = customer.toCustomerResDTO();
        } else {

            Customer customer = customerCreateDTO.toCustomer(locationRegion);
            customerRepository.save(customer);

            String str = uploadService.uploadFile(multipartFile);
            String fileUlr = str.split("=")[0];
            String cloudId = str.split("=")[1];

            Image image = new Image();
            image.setFileUrl(fileUlr);
            image.setCloudId(cloudId);
            imageRepository.save(image);
            customer.setImage(image);

            customerResDTO = customer.toCustomerResDTO();

        }
        return customerResDTO;
    }

    @Override
    public CustomerResDTO create(CustomerCreateDTO customerCreateDTO, MultipartFile[] multipartFile) throws IOException {
        return null;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public CustomerResDTO update(CustomerCreateDTO customerCreateDTO, MultipartFile multipartFile, Customer customer) throws IOException {
        CustomerResDTO customerResDTO = new CustomerResDTO();
        if (multipartFile == null) {

            LocationRegion locationRegion = customer.getLocationRegion();
            locationRegionRepository.save(locationRegion);
            Customer customerUp = customerCreateDTO.toCustomer(locationRegion);
            customerUp.setId(customer.getId());
            customerUp = customerRepository.save(customerUp);

            customerResDTO = customerUp.toCustomerResDTO();

        } else {
            LocationRegion locationRegion = customer.getLocationRegion();
            locationRegionRepository.save(locationRegion);
            Customer customerUp = customerCreateDTO.toCustomer(locationRegion);
            customerUp.setId(customer.getId());
            customerUp = customerRepository.save(customerUp);

            String str = uploadService.uploadFile(multipartFile);
            String fileUlr = str.split("=")[0];
            String cloudId = str.split("=")[1];

            Image image = new Image();
            image.setFileUrl(fileUlr);
            image.setCloudId(cloudId);
            imageRepository.save(image);
            customerUp.setImage(image);
            customerResDTO = customerUp.toCustomerResDTO();
        }
        return customerResDTO;
    }

    @Override
    public CustomerResDTO update(CustomerCreateDTO customerCreateDTO, MultipartFile[] multipartFile, Customer customer) throws IOException {
        return null;
    }
}
