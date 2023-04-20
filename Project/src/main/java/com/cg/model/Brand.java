package com.cg.model;




import com.cg.service.brand.BrandListResponse;
import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToOne(mappedBy = "brand")
    private Image image;

    public BrandListResponse toBrandListResponse(){
        return new BrandListResponse()
                .setId(id)
                .setName(name)
                .setFileUrl(image.getFileUrl());
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", image=" + image +
                '}';
    }
}
