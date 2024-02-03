package com.example.ordering.orderItem.domain;

import com.example.ordering.ordering.domain.Ordering;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    private Long id;


    @ManyToOne
    @JoinColumn
    private Ordering ordering;


}
