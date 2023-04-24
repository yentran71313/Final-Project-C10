package com.cg.api.customer;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.customer.Customer;
import com.cg.model.dto.customerDTO.CustomerCreateDTO;
import com.cg.model.dto.customerDTO.CustomerRequestDTO;
import com.cg.model.dto.customerDTO.CustomerResDTO;
import com.cg.service.customer.CustomerService;
import com.cg.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerAPI {

    private final CustomerService customerService;


    private final AppUtils appUtils;


    @GetMapping
    public ResponseEntity<Page<CustomerResDTO>> findAll(CustomerRequestDTO request, Pageable pageable) {
        return new ResponseEntity<>(customerService.getAllAndSearch(request, pageable), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@Validated CustomerCreateDTO customerCreateDTO, BindingResult bindingResult, MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
           return appUtils.mapErrorToResponse(bindingResult);
        }

        CustomerResDTO customerResDTO = customerService.create(customerCreateDTO, multipartFile);

        return new ResponseEntity<>(customerResDTO, HttpStatus.CREATED);
    }

    @PatchMapping("{idCustomer}")
    public ResponseEntity<CustomerResDTO> update(@PathVariable Long idCustomer, CustomerCreateDTO customerCreateDTO, MultipartFile multipartFiles, BindingResult bindingResult) throws IOException {
        Optional<Customer> customerOptional = customerService.findById(idCustomer);
        if (!customerOptional.isPresent()) {
            throw new ResourceNotFoundException("Customer not valid");
        }

        CustomerResDTO customerResDTO = customerService.update(customerCreateDTO, multipartFiles, customerOptional.get());
        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }
}
