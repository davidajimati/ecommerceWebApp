package com.personaproject.ecommercewebapp.repository;

import com.personaproject.ecommercewebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
