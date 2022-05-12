package com.magalu.Wishlist.gateways.repository;

import com.magalu.Wishlist.domains.WishedProduct;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WishedProductRepository extends MongoRepository<WishedProduct, ObjectId> {
    WishedProduct findByUuid(String uuid);
}
