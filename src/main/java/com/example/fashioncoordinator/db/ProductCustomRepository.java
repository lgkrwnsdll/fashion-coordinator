package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.db.query.CategorizedProductSelectQueryDto;
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

    public CategorizedProductSelectQueryDto findMinPriceProductByCategory(ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategorizedProductSelectQueryDto> query = cb.createQuery(
            CategorizedProductSelectQueryDto.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.construct(CategorizedProductSelectQueryDto.class,
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

    public CategorizedProductSelectQueryDto findMaxPriceProductByCategory(ProductCategory category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategorizedProductSelectQueryDto> query = cb.createQuery(
            CategorizedProductSelectQueryDto.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);

        String categoryColumn = category.getDbColumn();
        query
            .select(cb.construct(CategorizedProductSelectQueryDto.class,
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
