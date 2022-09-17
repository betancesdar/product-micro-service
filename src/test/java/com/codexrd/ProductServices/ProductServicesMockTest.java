package com.codexrd.ProductServices;

import com.codexrd.ProductServices.Entity.Category;
import com.codexrd.ProductServices.Entity.Product;
import com.codexrd.ProductServices.Repository.ProductRepository;
import com.codexrd.ProductServices.Services.ProductService;
import com.codexrd.ProductServices.Services.ServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

//Unit test with Mockito
@SpringBootTest
public class ProductServicesMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    //Method to test the update stock
    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ServiceImpl(productRepository);
        //Create an object
        Product Laptop = Product.builder()
                .id(1L)
                .name("laptop")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("1200.50"))
                .stock(Double.parseDouble("10"))
                .build();
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(Laptop));

        Mockito.when(productRepository.save(Laptop)).thenReturn(Laptop);

    }
    @Test
    public void whenValidateID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("laptop");
    }

    @Test
    //Validate the stock
    public void whenUpdateStock_returnNewStockQuantity(){
        Product newStock = productService.updateStock(1L,Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(Double.parseDouble("18.0"));
    }

}
