package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    Optional<Product> findByName(String mango);

    @Query("select e from Product e where e.name = ?1")
    Optional<Product> findByProductName(String title);


    Optional<Product> findByTitle(String fruit);

    Optional<Product> findByTitleLike(String fruit);

    Optional<Product> findByQuantityAndPrice(int i, BigDecimal bigDecimal);

    Optional<Product> findByQuantityOrPrice(int i, BigDecimal bigDecimal);



}
