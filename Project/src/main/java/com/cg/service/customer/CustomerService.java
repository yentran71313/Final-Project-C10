package com.cg.service.customer;

import com.cg.model.Image;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import com.cg.model.dto.customerDTO.CustomerCreateDTO;
import com.cg.model.dto.customerDTO.CustomerRequestDTO;
import com.cg.model.dto.customerDTO.CustomerResDTO;
import com.cg.repository.CustomerRepository;
import com.cg.repository.LocationRegionRepository;

import com.cg.service.baseservice.IBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements IBaseService<CustomerResDTO, CustomerRequestDTO, CustomerCreateDTO, Customer> {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Override
    public Page<CustomerResDTO> getAllAndSearch(CustomerRequestDTO request, Pageable pageable) {
        if(request.getSearch() != null){
            request.setSearch("%" + request.getSearch() + "%");
        }
        return customerRepository.getAllAndSearch(request, pageable);
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
