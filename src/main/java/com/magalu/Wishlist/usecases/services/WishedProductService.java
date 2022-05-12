package com.magalu.Wishlist.usecases.services;

import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;

import java.util.List;

public interface WishedProductService {

    void delete(String uuid);

    WishedProductResponse create(WishedProductRequest wishedProductRequest);

    WishedProductResponse getByUuid(String uuid);

    List<WishedProductResponse> getAll();
}
