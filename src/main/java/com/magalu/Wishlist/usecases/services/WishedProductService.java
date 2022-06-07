package com.magalu.Wishlist.usecases.services;

import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;

import java.util.List;

public interface WishedProductService {

    void deleteWishedProduct(String cpf, String uuid);

    WishedProductResponse addWishedProduct(WishedProductRequest wishedProductRequest);

    List<WishedProductResponse> getWishedProductListByCpf(String uuid);

    List<WishedProductResponse> getAll();
}
