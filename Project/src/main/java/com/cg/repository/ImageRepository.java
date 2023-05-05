package com.cg.repository;

import com.cg.model.Image;
import com.cg.model.customer.Customer;
import com.cg.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {



}
