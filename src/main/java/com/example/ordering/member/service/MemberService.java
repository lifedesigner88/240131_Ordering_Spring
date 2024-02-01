package com.example.ordering.member.service;

import com.example.ordering.member.domain.Address;
import com.example.ordering.member.domain.Member;
import com.example.ordering.member.domain.Role;
import com.example.ordering.member.dto.LoginReqDto;
import com.example.ordering.member.dto.MemberCreateReqDto;
import com.example.ordering.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepo;
    private final PasswordEncoder passEnco;

    @Autowired
    public MemberService(MemberRepository memberRepo, PasswordEncoder passEnco) {
        this.memberRepo = memberRepo;
        this.passEnco = passEnco;
    }

    public Member create(MemberCreateReqDto member) {

        Address address = new Address(
                member.getCity(),
                member.getStreet(),
                member.getZipcode()
        );

        Member newMember = Member.builder()
                .name(member.getName())
                .email(member.getEmail())
                .password(passEnco.encode(member.getPassword()))
                .address(address)
                .role(Role.USER)
                .build();

        return memberRepo.save(newMember);

    }

    public Member login(LoginReqDto reqDto) throws IllegalArgumentException {
        Member member = memberRepo.findByEmail(reqDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passEnco.matches(reqDto.getPassword(), member.getPassword()))
            throw new IllegalArgumentException("비밀번호 불일치");


        return member;

    }


}
