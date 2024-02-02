package com.example.ordering.item.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


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


    //    Time
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;
}
