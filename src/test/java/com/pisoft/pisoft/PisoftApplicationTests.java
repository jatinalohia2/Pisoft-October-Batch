package com.pisoft.pisoft;

import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.projection.CProductInfo;
import com.pisoft.pisoft.repository.ProductRepository;
import com.pisoft.pisoft.service.InsuranceService;
import com.pisoft.pisoft.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void showAllLogs(){
        insuranceService.hello();
    }

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

    @Test
    public void showAllProducts2(){

        // this is for interface projection

//        List<IProductInfo> productList = productRepository.findAllProductInfo();
//
//        for (IProductInfo p : productList){
//            System.out.println(p);
//        }

        // this is for concrete class:

        List<CProductInfo> productList = productRepository.findAllProductInfoConcrete();

        for (CProductInfo p : productList){
            System.out.println(p);
        }
    }

    @Test
    public void showProductId(){

//        productService.getAllProductIds();

//        productService.saveProduct();
        productService.updateProductById(1L);

    }


    @Test
    public void testLogs(){
        productService.showAllLogs();
    }


}
