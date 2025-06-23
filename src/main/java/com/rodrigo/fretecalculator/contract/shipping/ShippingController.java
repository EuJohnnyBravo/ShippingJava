package com.rodrigo.fretecalculator.contract.shipping;

import com.rodrigo.fretecalculator.contract.shipping.response.ShippingResponse;
import com.rodrigo.fretecalculator.contract.shipping.request.ShippingRequest;
import com.rodrigo.fretecalculator.implementation.service.ShippingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/orders")
    public ShippingResponse createShipping(@Valid @RequestBody ShippingRequest payload){
        return shippingService.createShipping(payload);
    }
}
