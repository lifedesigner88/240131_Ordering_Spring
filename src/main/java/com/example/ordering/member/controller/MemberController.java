package com.example.ordering.member.controller;

import com.example.ordering.common.ResponseDto;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.dto.LoginReqDto;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.service.MemberService;
import com.example.ordering.security.JwtTokenProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {

    private final MemberService service;
    private final JwtTokenProvide tokenProvider;

    public MemberController(@Autowired MemberService service,  JwtTokenProvide tokenProvider) {
        this.service = service;
        this.tokenProvider = tokenProvider;
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

        String jwtToken = tokenProvider.createdToken(
                member.getEmail(),
                member.getRole().toString());

        Map<String, Object> member_info = new HashMap<>();
            member_info.put("id", member.getId());
            member_info.put("token", jwtToken);

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
