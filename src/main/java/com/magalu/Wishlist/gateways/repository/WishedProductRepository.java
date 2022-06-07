package com.magalu.Wishlist.gateways.repository;

import com.magalu.Wishlist.domains.WishedProduct;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WishedProductRepository extends MongoRepository<WishedProduct, ObjectId> {
    WishedProduct findByClientCpfAndUuid(String cpf, String uuid);
    List<WishedProduct> findByClientCpf(String cpf);
}
