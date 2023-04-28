package com.cg.model.dto.customerDTO;


import com.cg.model.Image;
import com.cg.model.customer.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;

    private Image images;
    private LocationRegionDTO locationRegion;

    public CustomerResDTO(Long id, String fullName, String email, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
