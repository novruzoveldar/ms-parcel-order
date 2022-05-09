package com.guavapay.repository;

import com.guavapay.model.entity.ParcelItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelItemRepository extends JpaRepository<ParcelItem, Long> {
}
