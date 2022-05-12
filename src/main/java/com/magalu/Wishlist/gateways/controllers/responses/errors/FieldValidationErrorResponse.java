package com.magalu.Wishlist.gateways.controllers.responses.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FieldValidationErrorResponse {
    private String field;
    private String errorMessage;
}
