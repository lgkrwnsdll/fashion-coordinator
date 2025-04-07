package com.example.fashioncoordinator.db;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("""
            SELECT p FROM ProductEntity p
            WHERE p.tops IS NOT NULL
                AND p.outerwear IS NOT NULL
                AND p.pants IS NOT NULL
                AND p.sneakers IS NOT NULL
                AND p.bag IS NOT NULL
                AND p.hat IS NOT NULL
                AND p.socks IS NOT NULL
                AND p.accessories IS NOT NULL
            ORDER BY
                (p.tops + p.outerwear + p.pants + p.sneakers + p.bag + p.hat + p.socks + p.accessories) ASC,
                p.brand DESC
            LIMIT 1
        """)
    Optional<ProductEntity> findMinTotalPriceBrandProduct();

    Optional<ProductEntity> findByBrand(String brand);
}
