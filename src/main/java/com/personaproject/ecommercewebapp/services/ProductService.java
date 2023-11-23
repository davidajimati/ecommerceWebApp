package com.personaproject.ecommercewebapp.services;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.CategoryNotFoundException;
import com.personaproject.ecommercewebapp.execeptions.customExceptions.ProductNotFoundException;
import com.personaproject.ecommercewebapp.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ResponseServices responseServices;
    private final ProductRepo productRepo;
    private final CategoryService categoryService;

    public ResponseEntity<?> createProduct(ProductDTO productDTO) {
        categoryService.checkIfCategoryExists(productDTO.getCategoryId());

        Product product = new Product(productDTO.getProductName(), productDTO.getProductDescription(), productDTO.getCategoryId(), productDTO.getImageUrl(), productDTO.getPrice());
        productRepo.save(product);

        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.CREATED, "Product added to category with ID" + productDTO.getCategoryId()), HttpStatus.CREATED);
    }

    private ProductDTO getProductDTO(Product product) {
        return new ProductDTO(product.getProductName(), product.getProductDescription(),
                product.getCategoryId(), product.getImageUrl(), product.getPrice());
    }

    public ProductDTO findProductByID(@PathVariable Long productId) {
        Optional<Product> product = productRepo.findById(productId);

        if (product.isPresent()) return getProductDTO(product.get());
        throw new ProductNotFoundException("Product with ID " + productId + " cannot be found");
    }

    public void checkIfProductExists(Long productId) {
        if (findProductByID(productId) == null)
            throw new CategoryNotFoundException("Product with ID " + productId + " Cannot be found");
    }

    public List<ProductDTO> listAll() {
        ArrayList<ProductDTO> productList = new ArrayList<>();
        List<Product> rawProductList = productRepo.findAll();

        for (Product product : rawProductList) {
            productList.add(getProductDTO(product));
        }
        return productList;
    }

    public ResponseEntity<?> updateProduct(Long productId, ProductDTO productDTO) {
        checkIfProductExists(productId);

        Product product = productRepo.getReferenceById(productId);
        product.setCategoryId(productDTO.getCategoryId());
        product.setProductName(productDTO.getProductName());
        product.setImageUrl(productDTO.getImageUrl());

        productRepo.save(product);
        return new ResponseEntity<>(responseServices.apiResponse(HttpStatus.OK, "product with ID " + productId + " updated!"), HttpStatus.ACCEPTED);
    }


    public ResponseEntity<?> deleteProduct(Long productId) {
        checkIfProductExists(productId);
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
