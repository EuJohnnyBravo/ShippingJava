package com.rodrigo.fretecalculator.contract.shipping.response;

import java.util.Date;
import java.util.UUID;

public record ShippingResponse(
        UUID id,
        String clientName,
        String city,
        String estate,
        double totalWeight,
        double shippingPrice,
        Date timestamp
) {
}
