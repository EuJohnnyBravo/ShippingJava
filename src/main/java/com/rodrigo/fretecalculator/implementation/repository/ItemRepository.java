package com.rodrigo.fretecalculator.implementation.repository;

import com.rodrigo.fretecalculator.implementation.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
