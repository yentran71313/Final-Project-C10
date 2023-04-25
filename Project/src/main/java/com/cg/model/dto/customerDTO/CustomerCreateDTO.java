package com.cg.model.dto.customerDTO;

import com.cg.model.Image;
import com.cg.model.customer.Customer;
import com.cg.model.customer.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerCreateDTO {

    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Email is invalid")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "(0|0[3|5|7|8|9])+([0-9]{9})", message = "Phone number is invalid")
    private String phoneNumber;

    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;

    @NotBlank(message = "Address is required")
    private String address;

    private Long image;



    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setImage(new Image().setId(image))
                .setLocationRegion(new LocationRegion(null,provinceId,provinceName,districtId,districtName,wardId,wardName,address))
                ;
    }
}
