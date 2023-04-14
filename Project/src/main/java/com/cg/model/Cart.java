package com.cg.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE carts SET deleted=true WHERE id=?")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customerCart;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItemList;
}
