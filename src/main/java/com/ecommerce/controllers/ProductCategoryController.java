package com.ecommerce.controllers;

import com.ecommerce.entities.ProductCategory;
import com.ecommerce.entities.ProductCategory;
import com.ecommerce.entities.YesNo;
import com.ecommerce.services.ProductCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/productCategories")
@Tag(name = "Product Category API", description = "EndPoint for Products Categories")
public class ProductCategoryController extends BaseController {
    private ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories(@RequestParam(required = false, name = "name") String name) {
        try {
            List<ProductCategory> productCategories = productCategoryService.getList();
            if (productCategories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productCategories, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductCategory> findProductById(@PathVariable long id) {
        Optional<ProductCategory> optionalProductCategory = productCategoryService.findById(id);
        return optionalProductCategory.map(productCategory -> new ResponseEntity<>(productCategory, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<ProductCategory> createCategory(@Valid @RequestBody ProductCategory category) {
        try {
            ProductCategory productCategory = productCategoryService.save(category);
            return new ResponseEntity<>(productCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<ProductCategory> updateCategory(@RequestBody ProductCategory category) {
        if (category.getId() != null) {
            Optional<ProductCategory> optionalProductCategory = productCategoryService.findById(category.getId());
            if (optionalProductCategory.isPresent()) {
                ProductCategory productCategory = optionalProductCategory.get();
                productCategory = productCategoryService.save(productCategory);
                return new ResponseEntity<>(productCategory, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id) {
        try {
            Optional<ProductCategory> optionalProductCategory = productCategoryService.findById(id);
            if (optionalProductCategory.isPresent()) {
                ProductCategory product = optionalProductCategory.get();
                product.setDeleted(YesNo.YES);
                productCategoryService.save(product);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
