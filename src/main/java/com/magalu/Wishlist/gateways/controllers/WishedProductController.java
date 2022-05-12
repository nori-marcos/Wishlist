package com.magalu.Wishlist.gateways.controllers;

import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import com.magalu.Wishlist.gateways.controllers.responses.WishedProductResponse;
import com.magalu.Wishlist.usecases.services.WishedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wished-product")
public class WishedProductController {

    @Autowired
    WishedProductService wishedProductService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public WishedProductResponse addProductToWishlist(@RequestBody @Valid WishedProductRequest wishedProductRequest) {
        return wishedProductService.create(wishedProductRequest);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deleteProductFromWishlist(@PathVariable(required = true) String uuid) {
        wishedProductService.delete(uuid);
        return ResponseEntity.ok("Successfully, deleted.");
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public WishedProductResponse getWishedProductByUuid(@PathVariable(required = false) String uuid) {
        return wishedProductService.getByUuid(uuid);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<WishedProductResponse> getAllWishedProducts() {
        return wishedProductService.getAll();
    }

}
