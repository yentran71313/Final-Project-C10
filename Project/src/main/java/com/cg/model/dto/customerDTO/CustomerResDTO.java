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
    private String fileUrl;
    private String provinceName;
    private String districtName;
    private String wardName;
    private String provinceId;
    private String wardId;
    private String districtId;
    private String address;
    private Long avatarId;
    private LocationRegionDTO locationRegion;

    public CustomerResDTO(Long id, String fullName, String email, String phoneNumber, String fileUrl, String provinceName, String districtName, String wardName, String provinceId, String wardId, String districtId, String address,Long avatarId) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fileUrl = fileUrl;
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.wardName = wardName;
        this.provinceId = provinceId;
        this.wardId = wardId;
        this.districtId = districtId;
        this.address = address;
        this.avatarId = avatarId;
    }
}
