package com.example.ordering.item.controller;


import com.example.ordering.common.CommonResponse;
import com.example.ordering.item.dto.ItemResDto;
import com.example.ordering.item.dto.ItemSearchDto;
import com.example.ordering.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService service;
    public ItemController(@Autowired ItemService service) {
        this.service = service;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/item/create")
    public ResponseEntity<CommonResponse> itemCreate(){
        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.CREATED,
                        "Itme Succesfully create",
                        null
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/items/{id}/")
    public List<ItemResDto>items(ItemSearchDto itemSerchDto, Pageable pageable ){
        return null;
    }
}
