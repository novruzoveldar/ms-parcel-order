package com.guavapay.repository;

import com.guavapay.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelProductRepository extends JpaRepository<Product, Long> {
}
