package com.rodrigo.fretecalculator.contract.shipping.response;

import java.util.UUID;

public record ItemResponse(
        UUID id,
        String name,
        double weight,
        String clientName
) {
}
