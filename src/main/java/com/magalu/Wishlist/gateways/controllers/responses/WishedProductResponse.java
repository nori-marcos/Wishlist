package com.magalu.Wishlist.gateways.controllers.responses;

import com.magalu.Wishlist.domains.WishedProduct;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishedProductResponse {
    private String uuid;
    private String clientCpf;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private LocalDateTime inclusionDate;

    public WishedProductResponse(WishedProduct wishedProduct) {
        this.uuid = wishedProduct.getUuid();
        this.clientCpf = wishedProduct.getClientCpf();
        this.name = wishedProduct.getName();
        this.price = wishedProduct.getPrice();
        this.quantity = wishedProduct.getQuantity();
        this.inclusionDate = wishedProduct.getInclusionDate();
    }

    public static List<WishedProductResponse> toResponse(List<WishedProduct> wishedProductList) {
        return wishedProductList.stream().map(WishedProductResponse::new).collect(Collectors.toList());
    }

}
