package com.cg.model.customer;

import com.cg.model.BaseEntity;
import com.cg.model.Image;
import com.cg.model.Order;
import com.cg.model.dto.customerDTO.CustomerResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String email;

   @ManyToOne
   @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
   private LocationRegion locationRegion;

//    @OneToOne(mappedBy = "customerCart")
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "id",nullable = false)
//    private Order order;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;


    public CustomerResDTO toCustomerResDTO(){
        return new CustomerResDTO()
                .setId(id)
                .setFullName(fullName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                .setAvatarId(image.getId());
    }

}
