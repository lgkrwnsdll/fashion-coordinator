package com.example.fashioncoordinator.core.api.converter;

import com.example.fashioncoordinator.enums.ProductCategory;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;

public class ProductCategoryRequestConverter implements Converter<String, ProductCategory> {

    @Override
    public ProductCategory convert(@Nonnull String category) {
        return ProductCategory.ofKoreanName(category);
    }

}
