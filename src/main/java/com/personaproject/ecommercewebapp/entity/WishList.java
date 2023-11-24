package com.personaproject.ecommercewebapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @Column(name = "user_id")
    private User user;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "product_id")
    private List<Product> product;

    @Column(name = "column_id")
    private Date createdDate;

    public WishList(User user) {
        this.user = user;
        this.createdDate = new Date();
    }
}
