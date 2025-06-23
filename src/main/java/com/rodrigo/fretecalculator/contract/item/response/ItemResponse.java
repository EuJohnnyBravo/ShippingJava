package com.rodrigo.fretecalculator.contract.item.response;

import java.util.UUID;

public record ItemResponse(
        UUID id,
        String name,
        double weight,
        String clientName
) {
}
