package com.example.fashioncoordinator.api.customer;

import com.example.fashioncoordinator.api.customer.response.HighestLowestPriceBrandResponseDto;
import com.example.fashioncoordinator.api.customer.response.HighestLowestPriceBrandResponseDto.ProductResponseDto;
import com.example.fashioncoordinator.api.customer.response.LowestPriceBrandProductResponseDto;
import com.example.fashioncoordinator.api.customer.response.LowestPriceCombinationResponseDto;
import com.example.fashioncoordinator.db.ProductEntity;
import com.example.fashioncoordinator.db.ProductCustomRepository;
import com.example.fashioncoordinator.db.ProductJpaRepository;
import com.example.fashioncoordinator.enums.ProductCategory;
import com.example.fashioncoordinator.exception.CustomException;
import com.example.fashioncoordinator.exception.ProductErrorCode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;
    private final ProductCustomRepository productCustomRepository;

    public LowestPriceCombinationResponseDto getLowestPriceCombination() {
        List<ProductEntity> productEntityList = productJpaRepository.findAll();

        int totalPrice = 0;
        List<LowestPriceCombinationResponseDto.ProductResponseDto> productList = new ArrayList<>();
        for (ProductCategory category : ProductCategory.values()) {
            Optional<Entry<String, Integer>> minEntry = productEntityList.stream()
                .filter(p -> category.getPrice(p) != null)
                .collect(Collectors.toMap(ProductEntity::getBrand, category::getPrice))
                .entrySet()
                .stream()
                .min(Comparator.comparing(Map.Entry<String, Integer>::getValue)
                    .thenComparing(Map.Entry::getKey,
                        Comparator.reverseOrder()));  // 가격이 같으면 브랜드명 내림차순

            if (minEntry.isEmpty()) {
                throw new CustomException(ProductErrorCode.DATA_MISSING);
            }

            Entry<String, Integer> entry = minEntry.get();
            productList.add(
                LowestPriceCombinationResponseDto.ProductResponseDto.builder()
                    .category(category)
                    .brand(entry.getKey())
                    .price(entry.getValue())
                    .build()
            );

            totalPrice += entry.getValue();
        }

        return LowestPriceCombinationResponseDto.builder()
            .productList(productList)
            .totalPrice(totalPrice)
            .build();
    }

    public LowestPriceBrandProductResponseDto getLowestPriceBrandProducts() {
        ProductEntity productEntity = productJpaRepository.findLowestPriceBrand()
            .orElseThrow(() -> new CustomException(ProductErrorCode.DATA_MISSING));

        return LowestPriceBrandProductResponseDto.from(productEntity);
    }

    public HighestLowestPriceBrandResponseDto getHighestAndLowestPriceProducts(
        ProductCategory category) {
        try {
            ProductResponseDto minPriceProduct = productCustomRepository.findMinPriceProductByCategory(
                category);
            ProductResponseDto maxPriceProduct = productCustomRepository.findMaxPriceProductByCategory(
                category);

            return HighestLowestPriceBrandResponseDto.builder()
                .category(category)
                .lowestPriceProductList(List.of(minPriceProduct))
                .highestPriceProductList(List.of(maxPriceProduct))
                .build();
        } catch (EmptyResultDataAccessException e) {
            throw new CustomException(ProductErrorCode.DATA_MISSING);
        }
    }
}
