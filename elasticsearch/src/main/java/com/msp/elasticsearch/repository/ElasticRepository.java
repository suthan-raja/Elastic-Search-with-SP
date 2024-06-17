package com.msp.elasticsearch.repository;

import com.msp.elasticsearch.entity.ProductElastic;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticRepository extends ElasticsearchRepository<ProductElastic, Long> {

    @Query("{\"size\" : \"0\" , \"aggs\" : {\"id\" : {\"terms\" : { \"field\" : \"id.keyword\" }}}}")
    List<ProductElastic> findUniqueIdsWithLatestUpdatedAt();

    void deleteById(Long id);


   /* @Query(value = """
            {
                "bool" :{
                    "must" : [{ "multi_match" : { "query" : "?0" , "fields" " [ "id" , "name" , "description" , "brand" ] } } ],
                    "should" : [ { "match" : { "id" : "?0" } } ]
                   }
              }
              """)
    ProductElastic findByUniqueId(String uniqueId);*/

}
