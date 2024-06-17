package com.msp.elasticsearch.repository;

import com.msp.elasticsearch.entity.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ElasticJpaRepository extends JpaRepository<ProductJpa, Long> {


}
