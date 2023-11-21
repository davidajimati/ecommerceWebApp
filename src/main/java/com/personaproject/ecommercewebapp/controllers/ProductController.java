package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.repository.CategoryRepo;
import com.personaproject.ecommercewebapp.services.HandleAuthentication;
import com.personaproject.ecommercewebapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ResponseServices responseServices;
    private final HandleAuthentication handleAuthentication;
    private final ProductService productService;
    private final CategoryRepo categoryRepo;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestHeader String serviceToken,
                                @RequestHeader String authToken,
                                @RequestBody ProductDTO productDTO) {
        handleAuthentication.authenticateProductJob(authToken, serviceToken);
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get_all")
    public List<ProductDTO> getAllProducts() {
        return productService.listAll();
    }

    @GetMapping("/get_all_dev")
    public List<Product> devGetAllProducts() {
        return productService.devListAllProducts();
    }

    @GetMapping("/get/{productId}")
    public ProductDTO getById(@PathVariable Long productId) {
        return productService.findProductByID(productId);
    }

    @PutMapping("/update_product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId,
                                           @RequestBody ProductDTO productDTO,
                                           @RequestHeader String serviceToken,
                                           @RequestHeader String authToken) {
        handleAuthentication.authenticateProductJob(authToken, serviceToken);
        return new ResponseEntity<>(productService.updateProduct(productId, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<?> deleteAllProducts(@RequestHeader String authToken,
                                    @RequestHeader String serviceToken) {
        handleAuthentication.authenticateProductJob(authToken, serviceToken);
        return new ResponseEntity<>(productService.deleteAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }
}
