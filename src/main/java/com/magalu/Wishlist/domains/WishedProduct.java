package com.magalu.Wishlist.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.magalu.Wishlist.gateways.controllers.requests.WishedProductRequest;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "wishlist")
public class WishedProduct {

    @Id
    private ObjectId id;
    private String uuid = UUID.randomUUID().toString();
    private String name;
    private BigDecimal price;
    private Integer quantity;
    @JsonProperty("inclusion-date")
    private LocalDateTime inclusionDate = LocalDateTime.now();

    public WishedProduct(WishedProductRequest wishedProductRequest) {
        this.name = wishedProductRequest.getName();
        this.price = wishedProductRequest.getPrice();
        this.quantity = wishedProductRequest.getQuantity();
    }
}
