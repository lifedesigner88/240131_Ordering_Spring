package com.example.ordering.item.controller;


import com.example.ordering.common.CommonResponse;
import com.example.ordering.item.domain.Item;
import com.example.ordering.item.dto.ItemReqDto;
import com.example.ordering.item.dto.ItemResDto;
import com.example.ordering.item.dto.ItemSearchDto;
import com.example.ordering.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService service;
    public ItemController(@Autowired ItemService service) {
        this.service = service;
    }



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/items")
    public List<ItemResDto> items( ItemSearchDto itemSerchDto, Pageable pageable){

        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/item/create")
    public ResponseEntity<CommonResponse> itemCreate(ItemReqDto itemReqDto)  {
        Item item = service.createItem(itemReqDto);
        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.CREATED,
                        "Item Successfully Created",
                        null
                ),
                HttpStatus.CREATED
        );
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/items/{id}/image")
    public Resource getImage (@PathVariable Long id, ItemSearchDto itemSerchDto, Pageable pageable){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/item/{id}/delete")
    public ResponseEntity<CommonResponse> Delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.OK,
                        "Item successfully deleted",
                        null
                ),
                HttpStatus.OK
        );
    }



}
