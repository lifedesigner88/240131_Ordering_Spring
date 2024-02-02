package com.example.ordering.ordering.controller;

import com.example.ordering.common.CommonResponse;
import com.example.ordering.ordering.dto.OrderReqDto;
import com.example.ordering.ordering.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/order/create")
    public ResponseEntity<CommonResponse> createOrder(@RequestBody OrderReqDto dto) {
        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.CREATED,
                        "Item Successfully Created",
                        service.createOrder(dto)
                ),
                HttpStatus.CREATED
        );
    }

}
