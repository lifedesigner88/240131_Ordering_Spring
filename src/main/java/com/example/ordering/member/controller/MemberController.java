package com.example.ordering.member.controller;

import com.example.ordering.common.ResponseDto;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.dto.LoginReqDto;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MemberController {

    private final MemberService service;

    public MemberController(@Autowired MemberService service) {
        this.service = service;
    }


    @PostMapping("/member/create")
    public ResponseEntity<ResponseDto> memberCreate(@Valid @RequestBody MemberCreateReqDto dto){
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

    @PostMapping("/doLogin")
    public ResponseEntity<ResponseDto> memberLogin(@Valid @RequestBody LoginReqDto dto){
        Member member = service.login(dto);
        Map<String, Object> member_info = new HashMap<>();
            member_info.put("id", member.getId());
            member_info.put("name", member.getName());




        return new ResponseEntity<>(
                new ResponseDto(
                        HttpStatus.OK,
                        "member successfully logined",
                        member_info
                ),
                HttpStatus.OK
        );
    }


}
