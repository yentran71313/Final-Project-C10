package com.cg.model;

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

    private String phone;

    private String email;

    @OneToMany(mappedBy = "customer")
    private List<LocationRegion> locationRegionList;

    @OneToOne(mappedBy = "customerCart")
    private Cart cart;

    @OneToMany(mappedBy = "customerOrder" )
    private List<Order> orders;

}
