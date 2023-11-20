package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ResponseServices responseServices;
    private final ProductRepo productRepo;

    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        try {
            Product product = new Product();
            product.setProductDescription(productDTO.getProductDescription());
            product.setProductName(productDTO.getProductName());
            product.setImageUrl(productDTO.getImageUrl());
            product.setCategoryId(productDTO.getCategoryId());
            product.setPrice(productDTO.getPrice());

            productRepo.save(product);
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.CREATED, "Product added!"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.UNAUTHORIZED, "couldn't add product to category -> {}" + e), HttpStatus.UNAUTHORIZED);
        }
    }

    private ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductName(product.getProductName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setCategoryId(product.getCategoryId());
        return productDTO;
    }

    public List<ProductDTO> listAll() {
        List<ProductDTO> productList = new ArrayList<>();
        List<Product> rawProductList = productRepo.findAll();

        for(Product product: rawProductList) {
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    public ResponseEntity<?> updateProduct(Long productId, ProductDTO productDTO) {
        try {
            Product product = productRepo.getReferenceById(productId);
            product.setCategoryId(productDTO.getCategoryId());
            product.setProductName(productDTO.getProductName());
            product.setImageUrl(productDTO.getImageUrl());

            productRepo.save(product);
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK, "product with ID " + productId + " updated!"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.BAD_REQUEST, "Product cannot be updated"), HttpStatus.BAD_REQUEST);
        }
    }

    public ProductDTO findProductByID(@PathVariable Long productId) {
        return getProductDTO(productRepo.findById(productId).get());
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        productRepo.deleteById(productId);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK, "product with ID " + productId + " has been deleted!"), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAllProducts() {
        productRepo.deleteAll();
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK, "All products deleted"), HttpStatus.OK);
    }

    public List<Product> devListAllProducts() {
        return productRepo.findAll();
    }
}
