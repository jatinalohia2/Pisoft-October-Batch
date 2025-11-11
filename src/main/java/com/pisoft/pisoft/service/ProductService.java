package com.pisoft.pisoft.service;

import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

//    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public void placeOrder(Long userId, Long productId) {
        log.info("Starting order placement for userId={} and productId={}", userId, productId);
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (product.getQuantity() < 1) {
                log.warn("Product {} is out of stock", productId);
                return;
            }
            // Process order
            log.debug("Reducing quantity for product {}", productId);
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            log.info("Order placed successfully for userId={}", userId);
        } catch (Exception e) {
            log.error("Failed to place order for userId={} productId={}", userId, productId, e);
        }
    }

    public void showAllLogs(){
        log.info("this is info");
        log.error("this is error");
        log.warn("this is warm");
        log.debug("this is debug");
        log.trace("this is trace");
    }

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
