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

    @Override
    public Page<CustomerResDTO> getAllAndSearch(CustomerRequestDTO searchRequest, Pageable pageable) {
        return customerRepository.getAllAndSearch(searchRequest, pageable);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }

    @Override
    public void create(CustomerCreateDTO customerCreateDTO) {

        Customer customer = customerCreateDTO.toCustomer();
        locationRegionRepository.save(customer.getLocationRegion());
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerCreateDTO customerCreateDTO) {
        Optional<Customer> customer = customerRepository.findById(customerCreateDTO.getId());
        Customer customerUp = customerCreateDTO.toCustomer();
        locationRegionRepository.save(customerUp.getLocationRegion().setId(customer.get().getLocationRegion().getId()));
        customerRepository.save(customerUp);


    }

    @Override
    public void delete(Long id) {

    }


}
