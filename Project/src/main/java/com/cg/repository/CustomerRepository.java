package com.cg.repository;

import com.cg.model.customer.Customer;
import com.cg.model.dto.customerDTO.CustomerRequestDTO;
import com.cg.model.dto.customerDTO.CustomerResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT NEW com.cg.model.dto.customerDTO.CustomerResDTO (cus.id,cus.fullName, cus.email,cus.phoneNumber,cus.image.fileUrl,cus.locationRegion.provinceName,cus.locationRegion.districtName,cus.locationRegion.wardName,cus.locationRegion.address) " +
            "from Customer as cus " +
            "LEFT JOIN Image as img on cus.image.id = img.id " +
            "WHERE (:#{#request.search} is null or cus.fullName  like :#{#request.search} or :#{#request.search}  like cus.email) or :#{#request.search} like cus.phoneNumber " +
            "AND  (:#{#request.provinceId} is null or :#{#request.provinceId} = cus.locationRegion.provinceId) ")
    Page<CustomerResDTO> getAllAndSearch(CustomerRequestDTO request, Pageable pageable);


}
