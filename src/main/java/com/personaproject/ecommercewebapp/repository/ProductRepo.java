package com.personaproject.ecommercewebapp.repository;

import com.personaproject.ecommercewebapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
