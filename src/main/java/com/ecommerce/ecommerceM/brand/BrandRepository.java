package com.ecommerce.ecommerceM.brand;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends MongoRepository<BrandModel, Integer> {

}
