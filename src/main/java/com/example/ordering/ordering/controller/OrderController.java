package com.example.ordering.ordering.controller;

import com.example.ordering.common.CommonResponse;
import com.example.ordering.ordering.domain.Ordering;
import com.example.ordering.ordering.dto.OrderReqDto;
import com.example.ordering.ordering.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderController {

    private final OrderService service;
    public OrderController(@Autowired OrderService service) {
        this.service = service;
    }

    @PostMapping("/order/create")
    public ResponseEntity<CommonResponse> createOrder(@RequestBody OrderReqDto dto) {
        System.out.println("DTO TEST");
        log.info(dto.toString());
        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.CREATED,
                        "Item Successfully Created",
                        service.createOrder(dto)
                ),
                HttpStatus.CREATED
        );
    }


    @DeleteMapping("/order/{id}/cancel")
    public ResponseEntity<CommonResponse> cancelOrder(@PathVariable Long id) {
        Ordering ordering = service.cancelOrder(id);

        return new ResponseEntity<>(
                new CommonResponse(
                        HttpStatus.CREATED,
                        "Item Successfully Created",
                        ordering
                ),
                HttpStatus.CREATED
        );
    }

}


