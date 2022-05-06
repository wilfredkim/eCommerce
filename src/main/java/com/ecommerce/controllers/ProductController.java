package com.ecommerce.controllers;

import com.ecommerce.entities.Product;
import com.ecommerce.entities.YesNo;
import com.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/products")
public class ProductController extends BaseController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false, name = "productName") String productName) {
        try {
            List<Product> productList = productService.getList();
            if (productList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable long id) {
        Optional<Product> optionalProduct = productService.findById(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            Product saveProduct = productService.save(product);
            return new ResponseEntity<>(saveProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            Optional<Product> optionalProduct = productService.findById(product.getId());
            if (optionalProduct.isPresent()) {
                Product saveProduct = optionalProduct.get();
                saveProduct = productService.save(saveProduct);
                return new ResponseEntity<>(saveProduct, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            Optional<Product> optionalProduct = productService.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setDeleted(YesNo.YES);
                productService.save(product);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
