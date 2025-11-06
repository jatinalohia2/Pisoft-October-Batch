package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    @Transactional
    public void getAllProductIds() {

        Product product = productRepository.findById(1L).get();
        Product product2 = productRepository.findById(1L).get();

        System.out.println(product == product2);


    }


    public void saveProduct(){

        Product product = Product.builder()
                .name("io")
                .title("yt")
                .price(BigDecimal.valueOf(2000))
                .quantity(5)
                .build();

        Product saveProduct = productRepository.save(product);

        saveProduct.setName("pp");  // dirty checking
        saveProduct.setTitle("pp");  // dirty checking

    }

    @Transactional
    public void updateProductById(long l) {

        productRepository.updateProductById(l , "amit");


    }
}
