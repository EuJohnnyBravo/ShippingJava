package com.rodrigo.fretecalculator.implementation.service;

import com.rodrigo.fretecalculator.common.exception.custom.ResourceNotFoundException;
import com.rodrigo.fretecalculator.contract.item.response.AllItemsResponse;
import com.rodrigo.fretecalculator.contract.item.response.ItemResponse;
import com.rodrigo.fretecalculator.implementation.model.Item;
import com.rodrigo.fretecalculator.implementation.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemResponse getItem(String id){
        Item item = itemRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getWeight(),
                item.getOrder_id().getClientName()
        );
    }

    public AllItemsResponse getAllItems(){
        List<ItemResponse> items = itemRepository.findAll()
                .stream()
                .map(item -> new ItemResponse(
                        item.getId(),
                        item.getName(),
                        item.getWeight(),
                        item.getOrder_id().getClientName()
                )).toList();
        return new AllItemsResponse(items);
    }
}
