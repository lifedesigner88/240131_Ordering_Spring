package com.example.ordering.item.dto;


import lombok.Data;

@Data
public class ItemResDto {

    private Long id;

    private String name;
    private String category;

    private int price;
    private int stockQuantity;

    private String imagePath;

}
