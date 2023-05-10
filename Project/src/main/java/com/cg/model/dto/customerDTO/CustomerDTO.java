package com.cg.model.dto.customerDTO;

import com.cg.model.Image;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CustomerDTO {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;

    private LocationRegionDTO locationRegion;

    public Image image;

    public CustomerDTO(Long id, String fullName, String phoneNumber, String email, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public CustomerDTO(Long id, String fullName, String phoneNumber, String email, Image image) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
    }
    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}
