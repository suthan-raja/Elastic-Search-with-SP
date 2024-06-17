package com.msp.elasticsearch.service;

import com.msp.elasticsearch.entity.ProductElastic;
import com.msp.elasticsearch.repository.ElasticRepository;

import org.elasticsearch.common.util.CollectionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ElasticSearchService {

    @Autowired
    private ElasticRepository elasticRepository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    public Optional<ProductElastic> getById(String id){
        return elasticRepository.findById(Long.valueOf(id));
    }



    public List<ProductElastic> getAll(){
        Iterable<ProductElastic> products =  elasticRepository.findAll();
        List<ProductElastic> product = CollectionUtils.iterableAsArrayList(products);
       return product;
    }

    public List<ProductElastic> getuniqueValues(){
        return elasticRepository.findUniqueIdsWithLatestUpdatedAt();
    }

    public List<ProductElastic> getProducts(String id){
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery(id, "id","name","description","brand","created_at","updated_at"))
                .should(QueryBuilders.matchQuery("id",id));

        NativeSearchQuery  searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();

        SearchHits<ProductElastic> searchHits = elasticsearchOperations.search(searchQuery, ProductElastic.class);

        return searchHits.getSearchHits().stream().map(hit -> hit.getContent()).distinct().collect(Collectors.toList());
    }



    public void deletById(Long id){
        elasticRepository.deleteById(id);
    }


}
