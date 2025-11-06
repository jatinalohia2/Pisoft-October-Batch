package com.pisoft.pisoft.controller;

import com.pisoft.pisoft.annotion.Skip;
import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.repository.ProductRepository;
import com.pisoft.pisoft.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Skip
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    private final int PAGE_SIZE = 5;

    // 1. with the help Sort class

    @GetMapping
    public List<Product> productList(@RequestParam(defaultValue = "id") String sortBy ,
                                     @RequestParam(defaultValue = "price") String sortBy2,
                                     @RequestParam(defaultValue = "price") String sortBy3){

        // only one directional :
        Sort sort1 = Sort.by(Sort.Direction.ASC, sortBy , sortBy2);
        Sort sort = Sort.by(Sort.Order.asc(sortBy), Sort.Order.desc(sortBy2)  , Sort.Order.asc(sortBy3));
        return productRepository.findAll(sort);
    }

    @GetMapping("/getAll")
    public List<Product> productList1(@RequestParam(defaultValue = "0") int pageNumber ,
                                      @RequestParam(defaultValue = "price") String sortBy){


        Sort sort = Sort.by(Sort.Order.desc(sortBy));
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE , sort);

        return productRepository.findAll(pageable).getContent();
    }

    @GetMapping("/id")
    public String getAllProductId(){

        productService.getAllProductIds();
        return "getting data";

    }

}
