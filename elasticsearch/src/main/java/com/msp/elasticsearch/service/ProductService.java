package com.msp.elasticsearch.service;

import com.msp.elasticsearch.dto.ProductDto;
import com.msp.elasticsearch.entity.ProductJpa;
import com.msp.elasticsearch.mapper.ProductMapper;
import com.msp.elasticsearch.repository.ElasticJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {


    @Autowired
    ElasticJpaRepository ela;

    @Autowired
    ElasticJpaRepository repo;

    public ProductJpa getById(String id) {
        ProductJpa productJpa = new ProductJpa();
        productJpa = ela.findById(Long.valueOf(id)).get();
        return productJpa;
    }

    public ProductJpa saveProduct(ProductDto pro){

        System.out.println("Product Incoming : "+pro.toString());
        ProductJpa jpa = ela.save(ProductMapper.dto2jpa(pro));
        repo.save(jpa);
        Long idVal = jpa.getId();
        return null;
    }

    public ProductJpa updateProduct(ProductJpa jpa){
        ProductJpa product = new ProductJpa();
        product.setId(jpa.getId());
        product.setName(jpa.getName());
        product.setDescription(jpa.getDescription());
        product.setBrand(jpa.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        ela.save(product);

        return  product;
    }

    public String deleteProduct(Long id){
        ela.deleteById(id);
        return "Product Deleted";
    }
}
