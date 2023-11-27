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
    @PrimaryKeyJoinColumn(name = "user_id")
    private User user;

    @OneToMany(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "product_id")
    private List<Product> productList;

    @Column(name = "column_id")
    private Date createdDate;

    public WishList(User user, Product product) {
        this.user = user;
        this.createdDate = new Date();
        this.productList.add(product);
    }
}
