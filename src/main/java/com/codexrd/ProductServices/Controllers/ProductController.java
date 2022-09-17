package com.codexrd.ProductServices.Controllers;

import com.codexrd.ProductServices.Entity.Category;
import com.codexrd.ProductServices.Entity.Product;
import com.codexrd.ProductServices.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    //Get list all the product
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();
         products = productService.listAllProduct();
        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }
    //Get only one product
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if(null == product){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    //Create a product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productCreate = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }
    //Update product
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable  Long id,@RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    //delete a product
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Product productDelete = productService.deleteProduct(id);
        if (productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);

    }

    //update stock
    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity){
        Product product = productService.updateStock(id,quantity);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

}
