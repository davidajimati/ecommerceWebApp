package com.personaproject.ecommercewebapp.controllers;

import com.personaproject.ecommercewebapp.common.ResponseServices;
import com.personaproject.ecommercewebapp.dtos.ProductDTO;
import com.personaproject.ecommercewebapp.entity.Product;
import com.personaproject.ecommercewebapp.services.HandleAuthentication;
import com.personaproject.ecommercewebapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ResponseServices responseServices;
    private final HandleAuthentication handleAuthentication;
    private final ProductService productService;

    @PostMapping("/create")
    public Object createProduct(@RequestHeader String serviceToken,
                                @RequestHeader String authToken,
                                @RequestBody ProductDTO productDTO) {
        if (handleAuthentication.authenticateProductJob(authToken, serviceToken))
            return productService.createProduct(productDTO);

        return responseServices.apiResponse(HttpStatus.EXPECTATION_FAILED,
                false, "Token(s) cannot be validated");
    }

    @GetMapping("/get_all")
    public List<Product> getAllProducts() {
        return productService.listAll();
    }

    @PutMapping("/update_product/{productId}")
    public Object updateProduct(@PathVariable Long productId,
                                @RequestBody ProductDTO productDTO,
                                @RequestHeader String serviceToken,
                                @RequestHeader String authToken) {
        if (handleAuthentication.authenticateProductJob(authToken, serviceToken))
            return productService.updateProduct(productId, productDTO);
        return responseServices.apiResponse(HttpStatus.EXPECTATION_FAILED, false,
                "Token(s) cannot be validated");
    }

    @DeleteMapping("/delete_all")
    public Object deleteAllProducts(@RequestHeader String authToken,
                                    @RequestHeader String serviceToken) {
        if (handleAuthentication.authenticateProductJob(authToken, serviceToken))
            return productService.deleteAllProducts();
        return responseServices.apiResponse(HttpStatus.EXPECTATION_FAILED,
                false, "Token(s) cannot be validated");
    }

    @DeleteMapping("/delete/{product_id}")
    public Object deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
