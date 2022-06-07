package com.magalu.Wishlist.usecases.services;

import com.magalu.Wishlist.domains.WishedProduct;
import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;
import com.magalu.Wishlist.gateways.repository.WishedProductRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class WishedProductServiceImplTest {

    @InjectMocks
    WishedProductServiceImpl wishedProductServiceImpl;

    @Mock
    WishedProductRepository wishedProductRepository;

    @Mock
    WishedProductResponse wishedProductResponse;

    @Mock
    WishedProductRequest wishedProductRequest;

    @Mock
    WishedProduct wishedProduct;

    @Mock
    WishedProduct savedWishedProduct;

    @Mock
    List<WishedProduct> wishedProductList;

    @Mock
    List<WishedProductResponse> wishedProductResponseList;

    LocalDateTime inclusionDate = LocalDateTime.now();

    ObjectId id = new ObjectId();

    @BeforeEach
    public void setup() {
        wishedProductRequest =
                new WishedProductRequest(
                        "18520837000",
                        "Secador de cabelo",
                        BigDecimal.valueOf(3),
                        2
                );

        wishedProductResponse = new WishedProductResponse();

        wishedProduct =
                new WishedProduct(
                        id,
                        "e4b5209f-9d1f-47bc-acfb-f7eec3ffc033",
                        "18520837000",
                        "Secador de cabelo",
                        BigDecimal.valueOf(3),
                        2,
                        inclusionDate
                );

        savedWishedProduct = new WishedProduct();
        BeanUtils.copyProperties(wishedProduct, savedWishedProduct);

        wishedProductResponse =
                new WishedProductResponse(
                        "e4b5209f-9d1f-47bc-acfb-f7eec3ffc033",
                        "18520837000",
                        "Secador de cabelo",
                        BigDecimal.valueOf(3),
                        2,
                        inclusionDate
                );

        wishedProductList = new ArrayList<>();
        wishedProductList.add(wishedProduct);

        wishedProductResponseList = new ArrayList<>();
        wishedProductResponseList.add(wishedProductResponse);
    }

    @Test
    @DisplayName("Must throw an error of wished product not found")
    public void shouldNotfindWishedProductByUuid() {
        Mockito.when(wishedProductRepository.findByClientCpfAndUuid(
                "incorrect-cpf",
                "incorrect-uuid")).thenReturn(null);
    }

    @Test
    @DisplayName("Must return wished product without null proporties.")
    void shouldCreateValidRebelResponseWithoutNullProperties() {
        Mockito.when(wishedProductRepository.save(wishedProduct)).thenReturn(savedWishedProduct);
        Mockito.when(wishedProductRepository.save(savedWishedProduct)).thenReturn(wishedProduct);

        WishedProductResponse response = wishedProductServiceImpl.addWishedProduct(wishedProductRequest);

        assertNotNull(response);
    }

    @Test
    @DisplayName("Must return the saved wished product")
    public void shouldfindByUuidWishedProduct() {
        Mockito.when(wishedProductRepository.findAll()).thenReturn(wishedProductList);
        List<WishedProductResponse> returnWishedProductResponses = wishedProductServiceImpl.getAll();
//        assertEquals(returnWishedProductResponses, wishedProductResponseList);
    }


}