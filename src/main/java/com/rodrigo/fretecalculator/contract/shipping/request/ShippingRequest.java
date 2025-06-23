package com.rodrigo.fretecalculator.contract.shipping.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ShippingRequest(
        @NotBlank(message = "Client Name is needed")
        String clientName,
        @NotBlank(message = "Destination is required")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid destination. Expected format: 123456-789 or 123456789")
        String destination,
        @NotEmpty(message = "At least one item is required") @Valid
        List<ItemRequest> items
) {
}
