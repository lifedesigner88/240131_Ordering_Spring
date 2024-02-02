package com.example.ordering.ordering.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderReqDto {

    private List<OrderReqItemDto> orderReqItemDtoList;
    private static class OrderReqItemDto {
        private Long itemId;
        private int count;
    }


}

