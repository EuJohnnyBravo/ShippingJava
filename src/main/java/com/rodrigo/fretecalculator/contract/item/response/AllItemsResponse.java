package com.rodrigo.fretecalculator.contract.item.response;

public record AllItemsResponse(
        Iterable<ItemResponse> items
) {
}
