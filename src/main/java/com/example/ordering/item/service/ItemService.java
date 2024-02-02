package com.example.ordering.item.service;

import com.example.ordering.item.domain.Item;
import com.example.ordering.item.dto.ItemReqDto;
import com.example.ordering.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemService {

    private final ItemRepository itemRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }


    public Item createItem(ItemReqDto itemReqDto)  {
        MultipartFile multipartFile = itemReqDto.getItemImage();
        String fileName = multipartFile.getOriginalFilename();

        System.out.println(fileName);


        return null;
    }
}
