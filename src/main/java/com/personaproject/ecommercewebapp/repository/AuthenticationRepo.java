package com.personaproject.ecommercewebapp.repository;

import com.personaproject.ecommercewebapp.entity.AuthenticationToken;
import com.personaproject.ecommercewebapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepo extends JpaRepository<AuthenticationToken, Long> {

    public AuthenticationToken findByUser(User user);

}
