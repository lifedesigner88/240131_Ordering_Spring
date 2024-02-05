package com.example.ordering.item.domain;

import com.example.ordering.item.dto.ItemReqDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    private int price;
    private int stockQuantity;

    @Setter
    private String imagePath;

    @Builder.Default
    private String delYn = "N";

    public void delete() {
        this.delYn = "Y";
    }

    public void updateStockQuantity(int quantity) {
        this.stockQuantity += quantity;
    }

    public void update(ItemReqDto itemReqDto){
        this.name = itemReqDto.getName();
        this.category = itemReqDto.getCategory();
        this.price = itemReqDto.getPrice();
        this.stockQuantity = itemReqDto.getStockQuantity();
    }




    //    Time
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
