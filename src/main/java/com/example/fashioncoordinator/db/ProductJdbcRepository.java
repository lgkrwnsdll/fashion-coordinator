package com.example.fashioncoordinator.db;

import com.example.fashioncoordinator.api.customer.response.HighestLowestPriceBrandResponseDto;
import com.example.fashioncoordinator.enums.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public HighestLowestPriceBrandResponseDto.ProductResponseDto findProductByCategory(
        ProductCategory category, boolean lowest) {
        String column = category.getDbColumn();
        String order = lowest ? "ASC" : "DESC";
        String query = String.format(
            """
                SELECT brand, %s
                FROM product
                WHERE %s IS NOT NULL
                ORDER BY %s %s, brand DESC
                LIMIT 1
                """
            , column, column, column, order);

        return jdbcTemplate.queryForObject(query,
            (rs, rowNum) -> HighestLowestPriceBrandResponseDto.ProductResponseDto.builder()
                .brand(rs.getString("brand"))
                .price(rs.getInt(column))
                .build()
        );
    }
}
