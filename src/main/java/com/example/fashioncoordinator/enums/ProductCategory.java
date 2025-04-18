package com.example.fashioncoordinator.enums;

import com.example.fashioncoordinator.db.ProductEntity;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCategory {

    TOPS("tops", "상의"),
    OUTERWEAR("outerwear", "아우터"),
    PANTS("pants", "바지"),
    SNEAKERS("sneakers", "스니커즈"),
    BAG("bag", "가방"),
    HAT("hat", "모자"),
    SOCKS("socks", "양말"),
    ACCESSORIES("accessories", "액세서리");

    private final String dbColumn;
    @JsonValue
    private final String koreanName;

    public Integer getPrice(ProductEntity product) {
        return switch (this) {
            case TOPS -> product.getTops();
            case OUTERWEAR -> product.getOuterwear();
            case PANTS -> product.getPants();
            case SNEAKERS -> product.getSneakers();
            case BAG -> product.getBag();
            case HAT -> product.getHat();
            case SOCKS -> product.getSocks();
            case ACCESSORIES -> product.getAccessories();
        };
    }

    public static ProductCategory ofKoreanName(String name) {
        return Arrays.stream(ProductCategory.values())
            .filter(category -> category.getKoreanName().equals(name))
            .findFirst()
            .orElseThrow();
    }
}
