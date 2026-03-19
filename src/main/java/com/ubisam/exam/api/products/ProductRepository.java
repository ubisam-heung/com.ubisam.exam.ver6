package com.ubisam.exam.api.products;

import com.ubisam.exam.domain.Product;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

public interface ProductRepository extends RestfulJpaRepository<Product, Long>{
  
}
