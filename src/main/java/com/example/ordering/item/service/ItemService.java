package com.example.ordering.item.service;

import com.example.ordering.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }



}
