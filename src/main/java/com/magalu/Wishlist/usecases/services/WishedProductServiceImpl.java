package com.magalu.Wishlist.usecases.services;

import com.magalu.Wishlist.domains.WishedProduct;
import com.magalu.Wishlist.exceptions.BusinessValidationException;
import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;
import com.magalu.Wishlist.gateways.repository.WishedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishedProductServiceImpl implements WishedProductService {

    @Autowired
    private WishedProductRepository wishedProductRepository;

    @Override
    public WishedProductResponse create(WishedProductRequest wishedProductRequest) {

        List<WishedProduct> wishedProductList = wishedProductRepository.findAll();

        Integer totalNumberOfAddedItems = wishedProductList.stream()
                .mapToInt(wishedProduct -> wishedProduct.getQuantity()).sum();

        if (totalNumberOfAddedItems >= 20) {
            throw new BusinessValidationException("Your wish list has reached the limit of 20 items.", HttpStatus.BAD_REQUEST);
        }

        if (wishedProductRequest.getQuantity() > 20) {
            throw new BusinessValidationException("Your requested quantity is over the limit of 20 items.", HttpStatus.BAD_REQUEST);
        }

        WishedProduct wishedProduct = new WishedProduct(wishedProductRequest);

        wishedProduct = wishedProductRepository.save(wishedProduct);

        return new WishedProductResponse(wishedProduct);
    }

    @Override
    public void delete(String uuid) {
        WishedProduct wishedProduct = wishedProductRepository.findByUuid(uuid);

        if (wishedProduct == null) {
            throw new BusinessValidationException(
                    "Can not delete the product: " + uuid + " because it does not exist in the wishlist.", HttpStatus.NOT_FOUND);
        }

        wishedProductRepository.delete(wishedProduct);
    }

    @Override
    public WishedProductResponse getByUuid(String uuid) {

        WishedProduct wishedProduct = wishedProductRepository.findByUuid(uuid);

        if (wishedProduct == null) {
            throw new BusinessValidationException("Can not find the product: " + uuid + " in the wishlist.", HttpStatus.NOT_FOUND);
        }

        return new WishedProductResponse(wishedProduct);
    }

    @Override
    public List<WishedProductResponse> getAll() {

        List<WishedProduct> wishedProductList = wishedProductRepository.findAll();

        if (wishedProductList.isEmpty()) {
            throw new BusinessValidationException("The wishlist is empty.", HttpStatus.NOT_FOUND);
        }

        return WishedProductResponse.toResponse(wishedProductList);
    }

}
