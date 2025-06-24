package com.rodrigo.fretecalculator.implementation.service;

import com.rodrigo.fretecalculator.common.enums.ShippingByState;
import com.rodrigo.fretecalculator.common.exception.custom.InvalidRequestBodyException;
import com.rodrigo.fretecalculator.contract.shipping.response.ShippingResponse;
import com.rodrigo.fretecalculator.contract.shipping.request.ShippingRequest;
import com.rodrigo.fretecalculator.contract.viaCep.response.ViaCepResponse;
import com.rodrigo.fretecalculator.implementation.model.Item;
import com.rodrigo.fretecalculator.implementation.model.Order;
import com.rodrigo.fretecalculator.implementation.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingService {

    private final OrderRepository orderRepository;
    private final ViaCepService viaCepService;

    public ShippingResponse createShipping(ShippingRequest payload){

        Order order = setOrder(payload);
        List<Item> items = setItems(payload, order);

        order.setTotalWeight(calculateWeight(items));

        ViaCepResponse viaCep = viaCepService.getViaCep(payload.destination());
        order.setValue(ShippingByState.calculateShipping(viaCep.uf(), order.getTotalWeight()));
        order.setItems(items);
        orderRepository.save(order);

        return createShipping(order, viaCep);
    }

    private ShippingResponse createShipping(Order order, ViaCepResponse viaCep){
        return new ShippingResponse(
                order.getId(),
                order.getClientName(),
                viaCep.localidade(),
                viaCep.estado(),
                order.getTotalWeight(),
                order.getValue(),
                order.getCreatedAt()
        );
    }

    private Order setOrder(ShippingRequest payload){
        Order order = new Order();
        order.setClientName(payload.clientName());
        order.setDestination(payload.destination());
        order.setCreatedAt(new Date());
        return order;
    }

    private List<Item> setItems(ShippingRequest payload, Order order){
        return payload.items()
                .stream()
                .map(req -> {
                    Item item = new Item();
                    item.setName(req.getName());
                    item.setWeight(req.getWeight());
                    item.setOrder_id(order);
                    return item;
                }).toList();
    }

    private double calculateWeight(List<Item> items){
        double weight = items.stream().mapToDouble(Item::getWeight).sum();
        if(weight < 0 || weight > 50)
            throw new InvalidRequestBodyException("Total weight must be between 0Kg and 50Kg");
        return weight;
    }
}
