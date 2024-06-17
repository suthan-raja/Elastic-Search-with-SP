package com.msp.elasticsearch.controller;

import com.msp.elasticsearch.dto.ProductDto;
import com.msp.elasticsearch.entity.ProductElastic;
import com.msp.elasticsearch.entity.ProductJpa;
import com.msp.elasticsearch.service.ElasticSearchService;
import com.msp.elasticsearch.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/demo")
public class ElasticSearchController {


    Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    @Autowired
    ProductService productService;


    @Autowired
    ElasticSearchService elasticSearchService;

    @GetMapping("/get/jpa/{id}")
    public ProductElastic getMethod(@PathVariable("id") String id){
        ProductJpa jpa = productService.getById(id);
        System.out.println(jpa);
        ProductElastic elastic = elasticSearchService.getById(id).get();
        return elastic;
    }

    @GetMapping("/get/elastic")
    public List<ProductElastic> getElasticMethod(){
        List<ProductElastic> productElastics = elasticSearchService.getAll();
        productElastics.forEach(productElastic -> {
            System.out.println(productElastic.toString());
        });

        return productElastics;
    }


    @PostMapping("/save")
    public ProductJpa saveMethod(@RequestBody ProductDto product){
        return productService.saveProduct(product);
    }


    @PostMapping("/update")
    public ProductJpa updateMethod(@RequestBody ProductJpa product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/jpa/delete/{id}")
    public String deletemethod(@PathVariable("id") Long id){
        elasticSearchService.deletById(id);
        return productService.deleteProduct(id);
    }


    @GetMapping("/search/{id}")
    public List<ProductElastic> getProducts(@PathVariable("id") String id){
        return elasticSearchService.getProducts(id);
    }


    @DeleteMapping("/del/{id}")
    public void deleteById(@PathVariable("id") Long id){
        elasticSearchService.deletById(id);
    }

}
