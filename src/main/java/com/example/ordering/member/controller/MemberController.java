package com.example.ordering.member.controller;

import com.example.ordering.common.ResponseDto;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService service;

    public MemberController(@Autowired MemberService memberService) {
        this.service = memberService;
    }


    @PostMapping("/member/create")
    public ResponseEntity<ResponseDto> memberCreate(@RequestBody MemberCreateReqDto dto){
        Member member = service.create(dto);
        return new ResponseEntity<>(
                new ResponseDto(
                        HttpStatus.CREATED,
                        "member successfully created",
                        member.getId()
                ),
                HttpStatus.CREATED
        );
    }



}
