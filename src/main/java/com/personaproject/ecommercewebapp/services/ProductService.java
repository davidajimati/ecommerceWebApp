package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Category;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            product.setPrice(productDTO.getPrice());

            productRepo.save(product);
            return responseServices.apiResponse(HttpStatus.CREATED, true, "Product added!");
        } catch (Exception e) {
            return responseServices.apiResponse(HttpStatus.BAD_REQUEST, false, "couldn't add product to category -> {}" + e);
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

    @GetMapping("/get/productId")
    public ProductDTO findProductByID(@PathVariable Long productId) {
        return getProductDTO(productRepo.findById(productId).get());
    }

    public Object deleteProduct(Long productId) {
        productRepo.deleteById(productId);
        return responseServices.apiResponse(HttpStatus.OK, true, "product with ID " + productId + " has been deleted!");
    }

    public Object deleteAllProducts() {
        productRepo.deleteAll();
        return responseServices.apiResponse(HttpStatus.OK, true, "All products deleted");
    }

    public List<Product> devListAllProducts() {
        return productRepo.findAll();
    }
}
