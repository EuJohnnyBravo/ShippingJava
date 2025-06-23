package com.rodrigo.fretecalculator.contract.shipping;

import com.rodrigo.fretecalculator.contract.shipping.response.ItemResponse;
import com.rodrigo.fretecalculator.contract.shipping.response.ShippingResponse;
import com.rodrigo.fretecalculator.contract.shipping.request.ShippingRequest;
import com.rodrigo.fretecalculator.implementation.service.ItemService;
import com.rodrigo.fretecalculator.implementation.service.ShippingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/shipping/orders")
public class ShippingController {

    private final ShippingService shippingService;
    private final ItemService itemService;

    @PostMapping()
    public ShippingResponse createShipping(@Valid @RequestBody ShippingRequest payload){
        return shippingService.createShipping(payload);
    }

    @GetMapping("/{id}")
    public ItemResponse getItems(@PathVariable String id){
        return itemService.getItem(id);
    }
}
