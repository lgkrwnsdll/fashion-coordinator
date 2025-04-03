package com.example.fashioncoordinator.customer;

import com.example.fashioncoordinator.customer.response.LowestPriceCombinationResponseDto;
import com.example.fashioncoordinator.db.ProductEntity;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository productJpaRepository;

    public LowestPriceCombinationResponseDto getLowestPriceCombination() {
        List<ProductEntity> productEntityList = productJpaRepository.findAll();

        int totalPrice = 0;
        List<LowestPriceCombinationResponseDto.ProductResponseDto> productList = new ArrayList<>();
        for (ProductCategory category : ProductCategory.values()) {
            Optional<Entry<String, Integer>> minEntry = productEntityList.stream()
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
}
