package com.msp.elasticsearch.mapper;

import com.msp.elasticsearch.dto.ProductDto;
import com.msp.elasticsearch.entity.ProductElastic;
import com.msp.elasticsearch.entity.ProductJpa;

import java.time.LocalDateTime;

public class ProductMapper {

    public static ProductJpa dto2jpa(ProductDto productDto){
            return ProductJpa.builder().id(productDto.getId())
                    .name(productDto.getName()).description(productDto.getDescription())
                    .brand(productDto.getBrand()).createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now()).build();
    }

    public static ProductDto jpa2dto(ProductJpa productJpa){
        return  ProductDto.builder().id(productJpa.getId())
                .name(productJpa.getName()).description(productJpa.getDescription())
                .brand(productJpa.getBrand()).createdAt(productJpa.getCreatedAt())
                .updatedAt(productJpa.getUpdatedAt()).build();
    }

    public static ProductDto elastic2dto(ProductElastic productElastic){
        return ProductDto.builder()
                .id(productElastic.getId())
                .name(productElastic.getName()).
                 description(productElastic.getDescription())
                .brand(productElastic.getBrand())
                .createdAt(productElastic.getCreatedat())
                .updatedAt(productElastic.getUpdatedat())
                .build();
    }

    public static ProductElastic dto2elastic(ProductDto productDto){
        return ProductElastic.builder().id(productDto.getId())
                .name(productDto.getName()).description(productDto.getDescription())
                .brand(productDto.getBrand()).createdat(productDto.getCreatedAt())
                .updatedat(productDto.getUpdatedAt()).build();
    }

    public static ProductElastic jpa2elastic(ProductJpa productJpa){
        return ProductElastic.builder().id(productJpa.getId())
                .name(productJpa.getName()).description(productJpa.getDescription())
                .brand(productJpa.getBrand()).createdat(productJpa.getCreatedAt())
                .updatedat(productJpa.getUpdatedAt()).build();
    }
}
