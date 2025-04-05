package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.core.api.response.HighestLowestPriceBrandResponseDto;
import com.example.fashioncoordinator.core.api.response.HighestLowestPriceBrandResponseDto.ProductResponseDto;
import com.example.fashioncoordinator.enums.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepository {

    private final EntityManager em;

    public HighestLowestPriceBrandResponseDto.ProductResponseDto findMinPriceProductByCategory(
        ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductResponseDto> query = cb.createQuery(
            HighestLowestPriceBrandResponseDto.ProductResponseDto.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.construct(HighestLowestPriceBrandResponseDto.ProductResponseDto.class,
                root.get("brand"),
                root.get(categoryColumn)
            ))
            .where(cb.isNotNull(root.get(categoryColumn)))
            .orderBy(
                cb.asc(root.get(categoryColumn)),
                cb.desc(root.get("brand"))
            );

        return em.createQuery(query)
            .setMaxResults(1)
            .getSingleResult();
    }

    public HighestLowestPriceBrandResponseDto.ProductResponseDto findMaxPriceProductByCategory(
        ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductResponseDto> query = cb.createQuery(
            HighestLowestPriceBrandResponseDto.ProductResponseDto.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.construct(HighestLowestPriceBrandResponseDto.ProductResponseDto.class,
                root.get("brand"),
                root.get(categoryColumn)
            ))
            .where(cb.isNotNull(root.get(categoryColumn)))
            .orderBy(
                cb.desc(root.get(categoryColumn)),
                cb.desc(root.get("brand"))
            );

        return em.createQuery(query)
            .setMaxResults(1)
            .getSingleResult();
    }
}
