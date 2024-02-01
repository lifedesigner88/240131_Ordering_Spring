package com.example.ordering.member.controller;

import com.example.ordering.common.ResponseDto;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.dto.LoginReqDto;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.dto.MemberResponseDto;
import com.example.ordering.member.service.MemberService;
import com.example.ordering.security.TokenProviderJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MemberController {

    private final MemberService service;
    private final TokenProviderJwt tokenProvider;

    public MemberController(@Autowired MemberService service,  TokenProviderJwt tokenProvider) {
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

    @GetMapping("/members")
    public List<MemberResponseDto> members(){
        return service.findAll();
    }


//    @GetMapping("/member/{id}/orders")
//    public MemberResponseDto findMemberById(@PathVariable Long id) {
//
//    }
//
//    @GetMapping("/member/myorders")


    @GetMapping("/member/myInfo")
    public MemberResponseDto findMyInfo(){
        return service.findMyInfo();
    }



    @PostMapping("/doLogin")
    public ResponseEntity<ResponseDto> memberLogin(@Valid @RequestBody LoginReqDto dto){
        Member member = service.login(dto);

        String jwtToken = tokenProvider.createdToken(
                member.getEmail(),
                member.getRole().toString());

        Map<String, Object> info = new HashMap<>();
            info.put("id", member.getId());
            info.put("name", member.getName());
            info.put("adress", member.getAddress());
            info.put("token", jwtToken);

        return new ResponseEntity<>(
                new ResponseDto(
                        HttpStatus.OK,
                        "member successfully logined",
                        info
                ),
                HttpStatus.OK
        );
    }


}
