package com.rodrigo.fretecalculator.contract.item;

import com.rodrigo.fretecalculator.contract.item.response.AllItemsResponse;
import com.rodrigo.fretecalculator.contract.item.response.ItemResponse;
import com.rodrigo.fretecalculator.implementation.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public AllItemsResponse getItems(){
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ItemResponse getItem(@PathVariable String id){
        return itemService.getItem(id);
    }
}
