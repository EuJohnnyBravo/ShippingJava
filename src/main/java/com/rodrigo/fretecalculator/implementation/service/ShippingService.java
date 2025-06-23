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
        //1 - criar objeto Order
        Order order = new Order();
        ViaCepResponse viaCep;
        order.setClientName(payload.clientName());
        order.setDestination(payload.destination());
        order.setCreatedAt(new Date());

        //2 - preencher o Item (NÃO salvar no repositório ainda)
        List<Item> items = payload.items().stream().map(requestItem -> {
            Item item = new Item();
            item.setName(requestItem.getName());
            item.setWeight(requestItem.getWeight());
            item.setOrder_id(order);// <- faz a ligação bidirecional (Many to one)
            return item;
        }).toList();

        //3 - Calcula o peso total da order
        double totalWeight = items.stream().mapToDouble(Item::getWeight).sum();
        if(totalWeight < 0 || totalWeight > 50)
            throw new InvalidRequestBodyException("Total weight must be between 0Kg and 50Kg");
        order.setTotalWeight(totalWeight);

        //4 - Calcula o valor de Frete
        viaCep = viaCepService.getViaCep(payload.destination());

        double shippingPrice = ShippingByState.calculateShipping(viaCep.uf(), totalWeight);
        order.setValue(shippingPrice);

        //5 - setar os itens na order
        order.setItems(items);

        //6 - Salvar a order (os itens serão salvos juntos via Cascade)
        orderRepository.save(order);

        return new ShippingResponse(
                order.getId(),
                order.getClientName(),
                viaCep.localidade(),
                viaCep.estado(),
                totalWeight,
                shippingPrice,
                order.getCreatedAt()
        );
    }
}
