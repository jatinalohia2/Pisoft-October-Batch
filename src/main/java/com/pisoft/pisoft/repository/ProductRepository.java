package com.pisoft.pisoft.repository;

import com.pisoft.pisoft.entity.Product;
import com.pisoft.pisoft.projection.CProductInfo;
import com.pisoft.pisoft.projection.IProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
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

    List<Product> findAll(Sort sort);

    Page<Product> findAll(Pageable pageable);

    @Query("select p.id as id , p.name as name from Product p ")
    List<IProductInfo> findAllProductInfo();

    @Query("select new com.pisoft.pisoft.projection.CProductInfo(p.id , p.name) " +
            "from Product p ")
    List<CProductInfo> findAllProductInfoConcrete();

    @Modifying
    @Query("update Product set name = ?2 where id = ?1")
    void updateProductById(long l, String amit);
}
