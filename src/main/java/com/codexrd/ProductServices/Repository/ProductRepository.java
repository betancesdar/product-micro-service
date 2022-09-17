package com.codexrd.ProductServices.Repository;

import com.codexrd.ProductServices.Entity.Category;
import com.codexrd.ProductServices.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

    public List<Product> findByName(Product name);


}
