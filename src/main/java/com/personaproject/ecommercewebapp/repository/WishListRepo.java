package com.personaproject.ecommercewebapp.repository;

import com.personaproject.ecommercewebapp.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList, Integer> {
}
