package com.example.ordering.ordering.service;

import com.example.ordering.member.domain.Member;
import com.example.ordering.ordering.domain.Ordering;
import com.example.ordering.ordering.dto.OrderReqDto;
import com.example.ordering.ordering.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    public final OrderRepository repository;
    public OrderService(@Autowired OrderRepository repository) {
        this.repository = repository;
    }

    public Ordering createOrder(OrderReqDto orderReqDto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();;

        Ordering ordering = Ordering.builder()
                .member(new Member())
                .build();
        repository.save(ordering);
        return ordering;
    }


}
