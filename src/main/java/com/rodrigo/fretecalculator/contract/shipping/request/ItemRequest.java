package com.rodrigo.fretecalculator.contract.shipping.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Weight is required")
    private double weight;
}
