package com.magalu.Wishlist.gateways.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WishedProductRequest {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    @Min(1)
    private Integer quantity;
}
