package com.magalu.Wishlist.usecases.services;

import com.magalu.Wishlist.domains.WishedProduct;
import com.magalu.Wishlist.exceptions.BusinessValidationException;
import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;
import com.magalu.Wishlist.gateways.repository.WishedProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishedProductServiceImpl implements WishedProductService {

//    @Autowired
//    private WishedProductRepository wishedProductRepository;

    //    Utilização de constructor injection facilita a criação de testes, não necessitando de inflexão.
    private final WishedProductRepository wishedProductRepository;

    public WishedProductServiceImpl(final WishedProductRepository wishedProductRepository) {
        this.wishedProductRepository = wishedProductRepository;
    }

    @Override
    public WishedProductResponse addWishedProduct(WishedProductRequest wishedProductRequest) {

        List<WishedProduct> wishedProductList = wishedProductRepository.findByClientCpf(wishedProductRequest.getClientCpf());

        Integer totalNumberOfAddedItems =
                wishedProductList
                        .stream()
                        .mapToInt(wishedProduct -> wishedProduct.getQuantity()).sum();

        if (totalNumberOfAddedItems >= 20) {
            throw new BusinessValidationException(
                    "The wishlist of CPF "
                            + wishedProductRequest.getClientCpf()
                            + " has reached the limit of 20 items.", HttpStatus.BAD_REQUEST);
        }

        if (wishedProductRequest.getQuantity() > 20) {
            throw new BusinessValidationException("Your requested quantity is over the limit of 20 items.", HttpStatus.BAD_REQUEST);
        }

        WishedProduct wishedProduct = new WishedProduct(wishedProductRequest);

        wishedProduct = wishedProductRepository.save(wishedProduct);

        return new WishedProductResponse(wishedProduct);
    }

    @Override
    public void deleteWishedProduct(String cpf, String uuid) {
        WishedProduct wishedProduct = wishedProductRepository.findByClientCpfAndUuid(cpf, uuid);

        if (wishedProduct == null) {
            throw new BusinessValidationException(
                    "Can not delete the product "
                            + uuid
                            + " because it does not exist in the wishlist of the client "
                            + cpf
                            + ".", HttpStatus.NOT_FOUND);
        }

        wishedProductRepository.delete(wishedProduct);
    }

    @Override
    public List<WishedProductResponse> getWishedProductListByCpf(String cpf) {

        List<WishedProduct> wishedProductList = wishedProductRepository.findByClientCpf(cpf);

        if (wishedProductList.isEmpty()) {
            throw new BusinessValidationException(
                    "Can not find the wishlist of cpf "
                            + cpf
                            + ".", HttpStatus.NOT_FOUND);
        }

        return WishedProductResponse.toResponse(wishedProductList);
    }

    @Override
    public List<WishedProductResponse> getAll() {

        List<WishedProduct> wishedProductList = wishedProductRepository.findAll();

        if (wishedProductList.isEmpty()) {
            throw new BusinessValidationException("There are not wished products.", HttpStatus.NOT_FOUND);
        }

        return WishedProductResponse.toResponse(wishedProductList);
    }

}
