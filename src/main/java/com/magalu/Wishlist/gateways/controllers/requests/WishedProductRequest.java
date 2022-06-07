package com.magalu.Wishlist.gateways.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WishedProductRequest {
    @NotNull
    @CPF
    @Schema(description = "User cpf", example = "18520837000")
    private String clientCpf;

    @NotNull
    @Schema(description = "Product name", example = "Fridge")
    private String name;

    @NotNull
    @Schema(description = "Price in Brazilian real", example = "100.5")
    private BigDecimal price;

    @NotNull
    @Min(1)
    @Schema(description = "Quantity integer", example = "2")
    private Integer quantity;
}
