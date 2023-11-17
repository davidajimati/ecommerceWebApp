package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ResponseServices responseServices;
    private final ProductRepo productRepo;

    public Object createProduct(ProductDTO productDTO) {
        try {
            Product product = new Product();
            product.setProductDescription(productDTO.getProductDescription());
            product.setProductName(productDTO.getProductName());
            product.setImageUrl(productDTO.getImageUrl());
            product.setCategoryId(productDTO.getCategoryId());

            productRepo.save(product);
            return responseServices.apiResponse(HttpStatus.CREATED, true, "Product added!");
        } catch (Exception e) {
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "couldn't add product to category -> {}" + e);
        }
    }

    public List<Product> listAll() {
        return (productRepo.findAll());
    }

    public Object updateProduct(Long productId, ProductDTO productDTO) {
        try {
            Product product = productRepo.getReferenceById(productId);
            product.setCategoryId(productDTO.getCategoryId());
            product.setProductName(productDTO.getProductName());
            product.setImageUrl(productDTO.getImageUrl());

            productRepo.save(product);
            return responseServices.apiResponse(HttpStatus.OK, true, "product with ID " + productId + " updated!");
        } catch (Exception e) {
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "Product cannot be updated");
        }
    }

    public Object deleteProduct(Long productId) {
        productRepo.deleteById(productId);
        return responseServices.apiResponse(HttpStatus.OK, true, "product with ID " + productId + " has been deleted!");
    }

    public Object deleteAllProducts() {
        productRepo.deleteAll();
        return responseServices.apiResponse(HttpStatus.OK, true, "All products deleted");
    }
}
