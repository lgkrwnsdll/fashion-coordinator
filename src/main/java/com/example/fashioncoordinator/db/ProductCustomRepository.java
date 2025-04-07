package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.core.domain.Product;
import com.example.fashioncoordinator.enums.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepository {

    private final EntityManager em;

    public Product findMinPriceProductByCategory(ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.tuple(
                root.get("brand").alias("brand"),
                root.get(categoryColumn).alias("price")
            ))
            .where(cb.isNotNull(root.get(categoryColumn)))
            .orderBy(
                cb.asc(root.get(categoryColumn)),
                cb.desc(root.get("brand"))
            );

        Tuple tuple = em.createQuery(query)
            .setMaxResults(1)
            .getSingleResult();

        return Product.builder()
            .brand(tuple.get("brand", String.class))
            .price(tuple.get("price", Integer.class))
            .build();
    }

    public Product findMaxPriceProductByCategory(ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.tuple(
                root.get("brand").alias("brand"),
                root.get(categoryColumn).alias("price")
            ))
            .where(cb.isNotNull(root.get(categoryColumn)))
            .orderBy(
                cb.desc(root.get(categoryColumn)),
                cb.desc(root.get("brand"))
            );

        Tuple tuple = em.createQuery(query)
            .setMaxResults(1)
            .getSingleResult();

        return Product.builder()
            .brand(tuple.get("brand", String.class))
            .price(tuple.get("price", Integer.class))
            .build();
    }
}
