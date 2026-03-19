package com.ubisam.exam.api.productGroups;

import com.ubisam.exam.domain.ProductGroup;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

public interface ProductGroupRepository extends RestfulJpaRepository<ProductGroup, Long>{
  
}
