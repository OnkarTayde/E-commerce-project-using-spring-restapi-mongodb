package com.ecommerce.ecommerceM.category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends MongoRepository<CategoryModel, Integer> {

}
