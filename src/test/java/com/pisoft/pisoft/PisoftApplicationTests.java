package com.pisoft.pisoft;

import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PisoftApplicationTests {

    @Autowired
    private  ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void saveProduct(){

        Product product = Product.builder()
                .name("Mango")
                .title("furits")
                .price(BigDecimal.valueOf(2000))
                .quantity(5)
                .build();

        Product save = productRepository.save(product);
        System.out.println(save);
    }

    @Test
    void showAllProducts(){

        List<Product> productList = productRepository.findAll();

        productList.forEach(e-> System.out.println(e));
    }

    @Test
    void findBy(){
        Optional<Product> byId = productRepository.findByProductName("Apple");
        System.out.println(byId.get());

        Optional<Product> optional = productRepository.findByTitle("fruit");
        System.out.println(optional.get());

    }
    @Test
    void findByQuantityAndPrice(){

        Optional<Product> andPrice = productRepository.findByQuantityAndPrice(5, BigDecimal.valueOf(2000));
        System.out.println(andPrice.get());

        Optional<Product> byTitleLike = productRepository.findByTitleLike("%f%");



    }


}
