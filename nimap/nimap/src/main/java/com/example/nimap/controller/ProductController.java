package com.example.nimap.controller;

import com.example.nimap.entity.Product;
import com.example.nimap.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size) {
        Page<Product> products = productService.getAllProducts(PageRequest.of(page - 1, size));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{di}")
    public ResponseEntity<Product> getProductById(@PathVariable("di") Long id) throws Exception {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{di}")
    public ResponseEntity<Product> updateProduct(@PathVariable("di") Long id, @RequestBody Product productDetails) throws Exception {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{di}")
    public ResponseEntity<?> deleteProduct(@PathVariable("di") Long id) throws Exception {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted...id = " + id);
    }
}