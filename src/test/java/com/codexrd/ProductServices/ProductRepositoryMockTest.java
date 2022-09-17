package com.codexrd.ProductServices;

import com.codexrd.ProductServices.Entity.Category;
import com.codexrd.ProductServices.Entity.Product;
import com.codexrd.ProductServices.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    //Method to test our product Jpa
    @Test
    public void whenSearchByCategory_thenReturnListProduct (){
        Product product01 = Product.builder()
                .name("Computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1000"))
                .status("Created")
                .createdAt(new Date()).build();

        productRepository.save(product01);

        List<Product> productFounds = productRepository.findByCategory(product01.getCategory());


        Assertions.assertThat(productFounds.size()).isEqualTo(3);
    }
}
